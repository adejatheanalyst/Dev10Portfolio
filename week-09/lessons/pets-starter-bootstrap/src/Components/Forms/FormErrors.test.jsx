// Import react-testing-library methods
import { render, screen } from '@testing-library/react';
// Import component to test
import FormErrors from './FormErrors';

test('it renders null if no errors prop', () => {
    // Destructure the container div from the return of render()
    const { container } = render(<FormErrors />);
      // If our FormErrors component does not receive an errors prop,
  // it should return null and the container should be empty
  expect(container).toBeEmptyDOMElement();

  });

  // Add this test
test('it renders null if errors prop is empty', () => {
    // Pass an empty array `errors` prop to FormErrors
    const { container } = render(<FormErrors errors={[]} />);
    expect(container).toBeEmptyDOMElement();
  });


  test('it renders FormErrors with alert, heading, and li elements if errors not empty', () => {
    const errors = [
      'Type is required',
      'Vaccination status is required',
      'DOB cannot be in the future',
    ];
  
    // Arrange: Pass props to component and render it
    render(<FormErrors errors={errors} />);
  
    // Act
    const alert = screen.getByRole('alert');
    const heading = screen.getByRole('heading');
    const listItems = screen.getAllByRole('listitem');
  
    // Assert
    expect(alert).toHaveClass('alert alert-danger');
    expect(heading).toHaveTextContent(/the following errors occurred/i);
    expect(listItems).toHaveLength(errors.length);
  
    // Loop over the errors array
    for (const error of errors) {
      // Grab each error by its text
      const errorItem = screen.getByText(error);
      // Make sure it made it onto the screen
      expect(errorItem).toBeInTheDocument();
    }
    screen.debug();
  });
  