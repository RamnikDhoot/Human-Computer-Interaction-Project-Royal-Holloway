import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import HomePage from '../pages/Home';

//not testing the InventoryCard component here so i can mock it to simplify the test setup.
jest.mock("../components/InventoryCard", () => ({ id, title }) => (
    <div data-testid={`inventory-item-${id}`}>{title}</div>
  ));
  

describe('HomePage', () => {
  it('displays inventory items', () => {
    render(<HomePage />);
    const inventoryItems = screen.getAllByTestId(/inventory-item-/i);
    expect(inventoryItems).toHaveLength(6); 
  });

  it('adds a new inventory item when add button is clicked', () => {
    render(<HomePage />);
    const addButton = screen.getByRole('button', { name: /add inventory item/i });
    fireEvent.click(addButton);

    const inventoryItems = screen.getAllByTestId(/inventory-item-/i);
    expect(inventoryItems).toHaveLength(7); 
  });

});
