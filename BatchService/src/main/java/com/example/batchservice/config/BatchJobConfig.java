
package com.example.batchservice.config;

import com.example.batchservice.dto.BookRecord;
import com.example.batchservice.entity.BookEntity;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchJobConfig {

    @Bean
    public ItemReader<BookRecord> bookReader() {

        FlatFileItemReader<BookRecord> reader =
                new FlatFileItemReader<>();

        reader.setName("bookReader");
        reader.setResource(new ClassPathResource("books.csv"));
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer =
                new DelimitedLineTokenizer();

        tokenizer.setNames(
                "id", "title", "author", "isbn", "available"
        );

        FieldSetMapper<BookRecord> mapper = fieldSet ->
                new BookRecord(
                        fieldSet.readInt("id"),
                        fieldSet.readString("title"),
                        fieldSet.readString("author"),
                        fieldSet.readString("isbn"),
                        fieldSet.readString("available")
                );

        DefaultLineMapper<BookRecord> lineMapper =
                new DefaultLineMapper<>();

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);

        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public ItemProcessor<BookRecord, BookEntity> bookProcessor() {

        return record -> {
            BookEntity entity = new BookEntity();

            entity.setId(record.id());
            entity.setTitle(record.title());
            entity.setAuthor(record.author());
            entity.setIsbn(record.isbn());
            entity.setAvailable(
                    Boolean.parseBoolean(record.available())
            );

            return entity;
        };
    }

    @Bean
    public JdbcBatchItemWriter<BookEntity> bookWriter(
            DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<BookEntity>()
                .dataSource(dataSource)
                .sql("""
                        INSERT INTO book_record
                        (id, title, author, isbn, available)
                        VALUES
                        (:id, :title, :author, :isbn, :available)
                        """)
                .beanMapped()
                .build();
    }

    @Bean
    public Step bookImportStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<BookRecord> bookReader,
            ItemProcessor<BookRecord, BookEntity> bookProcessor,
            ItemWriter<BookEntity> bookWriter) {

        return new StepBuilder("bookImportStep", jobRepository)
                .<BookRecord, BookEntity>chunk(
                        10, transactionManager
                )
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(bookWriter)
                .build();
    }

    @Bean
    public Job bookImportJob(
            JobRepository jobRepository,
            Step bookImportStep) {

        return new JobBuilder("bookImportJob", jobRepository)
                .start(bookImportStep)
                .build();
    }
}
