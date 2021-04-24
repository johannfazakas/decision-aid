export async function handleResponse(response) {
  if (response.ok) {
    if (response.status === 204) {
      return {};
    } else {
      return response.json();
    }
  }
  // TODO refactor error handling
  if (response.status === 400) {
    const error = await response.text();
    throw new Error(error);
  }
}

export function handleError(error) {
  console.error("API call failed. " + error);
  throw error;
}
