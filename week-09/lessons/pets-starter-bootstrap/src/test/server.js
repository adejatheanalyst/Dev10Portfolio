// src/test/server.js

import { setupServer } from 'msw/node';
import { http, HttpResponse } from 'msw';
import pets from './pets.json';

const BASE_URL = 'http://localhost:8080/api/pets';

// Set up request interception layer
// https://mswjs.io/docs/api/setup-server
export const server = setupServer(
  // GET ALL PETS
  http.get(BASE_URL, () => {
    return HttpResponse.json(pets);
  }),

  // GET ONE PET
  http.get(`${BASE_URL}/:petId`, ({ request, params, cookies }) => {
    const { petId } = params;
    const pet = pets.find(p => p.petId === parseInt(petId));
    if (pet) {
      return HttpResponse.json(pet);
    } else {
      return new HttpResponse(null, {
        status: 404,
      });
    }
  }),

  // CREATE PET
  http.post(BASE_URL, () => {
    return new HttpResponse(null, {
      status: 201,
    });
  })
);

