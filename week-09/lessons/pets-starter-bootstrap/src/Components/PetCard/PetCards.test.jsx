import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import PetCards from './PetCards';

function renderComponent() {
  render(
    <MemoryRouter>
      <PetCards />
    </MemoryRouter>
  );
}

function pause() {
  // This promise resolves after a 500 ms delay
  return new Promise(resolve => setTimeout(resolve, 500));
}

// Add `async` keyword to the callback
test('it loads and displays pet information', async () => {
  renderComponent();

  await pause();

  screen.debug(); // What is our HTML markup after the pause?
});
