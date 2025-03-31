// Import react-testing-library methods
import { render, screen } from '@testing-library/react';
// Import component to test
import About from './About';

test('it renders About heading and paragraph', () => {
  // Arrange: Render a React element into the DOM
  render(<About />);

  // Act: Find the element using the screen object
  // Because querying the DOM is so common,
  // DOM Testing Library and wrappers including React Testing Library
  // export the screen object which gives us access to document.body
  const h1 = screen.getByText('About React Pets'); // Can match by exact string literal
  const p = screen.getByText(/welcome to react pets/i); // Can match by regex (partial and case insensitive match ok)

  // Assert: Verify element exists
  expect(h1).toBeInTheDocument();
  expect(p).toBeInTheDocument();
});
