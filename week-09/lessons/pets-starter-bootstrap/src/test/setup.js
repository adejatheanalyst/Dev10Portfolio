// ../src/test/setup.js
import '@testing-library/jest-dom'
// Import our customized mock server
import { server } from './test/server';

// Establish API mocking/request interception layer before all tests
beforeAll(() => server.listen());

// Reset any request handlers that are declared as a part of our tests
// (i.e. for testing one-time error scenarios, which we'll see later in this lesson)
afterEach(() => server.resetHandlers());

// Clean up once the tests are done
afterAll(() => server.close());
