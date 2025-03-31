import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import PetCard from './PetCard';

test('it displays pet information', () => {
  // Arrange
  // Render PetCard as a child of MemoryRouter to give it access to router context
  render(
    <MemoryRouter>
      <PetCard />
    </MemoryRouter>
  );
});

test('it displays pet information', () => {
    const pet = {
      petId: 1,
      name: 'Wishbone',
      type: 'DOG',
      breed: 'Jack Russell Terrier',
      dob: '2015-05-05',
      adopted: true,
      vaccinationStatus: 'UNKNOWN',
      imageUrl: 'https://i.imgur.com/yGzjvPj.jpg',
    };
  
    // Arrange
    render(
      <MemoryRouter>
        <PetCard pet={pet} />
      </MemoryRouter>
    );
  
// Act
const nameHeading = screen.getByRole('heading', {
    name: new RegExp(pet.name, 'i'),
  });
  const breedHeading = screen.getByRole('heading', {
    name: new RegExp(pet.breed, 'i'),
  });
  // Grab all the list items
  const listItems = screen.getAllByRole('listitem');
  // Look for the DOB, vaccinations, and adopted text content on the screen
  const dob = screen.getByText(new RegExp(pet.dob));
  const vaccinationStatus = screen.getByText(
    new RegExp(pet.vaccinationStatus.replaceAll('_', ' '), 'i')
  );
  const adoptedStatus = screen.getByText(
    `Adopted: ${pet.adopted ? 'yes' : 'not yet'}`
  );
  
  // Assert
  expect(nameHeading).toBeInTheDocument();
  expect(breedHeading).toBeInTheDocument();
  expect(listItems).toHaveLength(3);
  expect(dob).toBeInTheDocument();
  expect(vaccinationStatus).toBeInTheDocument();
  expect(adoptedStatus).toBeInTheDocument();
  
  
  });

    test('it displays a pet image with the correct src url and alt text', () => {
        // Arrange
        const { pet } = renderComponent();
      
        // Act
        const img = screen.getByAltText(`${pet.name}, a ${pet.breed}`);
      
        // Assert
        expect(img).toBeInTheDocument();
        expect(img).toHaveAttribute('src', pet.imageUrl);
      });
      


  function renderComponent() {
    const pet = {
      petId: 1,
      name: 'Wishbone',
      type: 'DOG',
      breed: 'Jack Russell Terrier',
      dob: '2015-05-05',
      adopted: true,
      vaccinationStatus: 'UNKNOWN',
      imageUrl: 'https://i.imgur.com/yGzjvPj.jpg',
    };
  
    render(
      <MemoryRouter>
        <PetCard pet={pet} />
      </MemoryRouter>
    );
  
    return { pet }; // return props that component was passed
  }
  
  
  
  
  
  
  

