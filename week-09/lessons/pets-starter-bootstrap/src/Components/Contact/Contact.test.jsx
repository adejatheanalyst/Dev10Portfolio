// Import react-testing-library methods
import { render, screen } from '@testing-library/react';
// Import component to test
import Contact from './Contact';

test('renders contact heading', () => {
  // Arrange: Render a React element into the DOM
  render(<Contact />);

  // Act: Find the element using the screen object
  // Because querying the DOM is so common,
  // DOM Testing Library and wrappers including React Testing Library
  // export the screen object which gives us access to document.body
  const heading = screen.getByRole('heading'); // Can match by exact string literal // Can match by regex (partial and case insensitive match ok)

  // Assert: Verify element exists
  expect(heading).toHaveTextContent(/contact us/i)

});
