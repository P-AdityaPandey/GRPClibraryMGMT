const BOOKS_API = '/api/library/books';

async function requestJson(url, options = {}) {
  let response;

  try {
    response = await fetch(url, {
      ...options,
      headers: {
        Accept: 'application/json',
        ...(options.headers || {})
      }
    });
  } catch {
    throw new Error(
      'Cannot reach LibraryService. Confirm it is running on http://localhost:8080 and try again.'
    );
  }

  if (!response.ok) {
    const body = await response.text().catch(() => '');
    let message = body;
    try {
      const parsed = JSON.parse(body);
      message = parsed.message || parsed.error || body;
    } catch {
      // Keep the raw response body when it is not JSON.
    }
    throw new Error(message || `Request failed with HTTP ${response.status}.`);
  }

  if (response.status === 204) return null;

  const contentType = response.headers.get('content-type') || '';
  if (!contentType.includes('application/json')) {
    const body = await response.text();
    throw new Error(
      `Expected JSON from ${url}, but received a different response. Check the LibraryService endpoint.`
    );
  }

  return response.json();
}

export const bookApi = {
  list: () => requestJson(BOOKS_API),
  getById: (id) => requestJson(`${BOOKS_API}/${encodeURIComponent(id)}`),
  availability: (id) => requestJson(`${BOOKS_API}/${encodeURIComponent(id)}/availability`)
};