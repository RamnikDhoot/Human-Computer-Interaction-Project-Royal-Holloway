import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import HomePage from '../pages/Home';

// Mocking the InventoryCard component to simplify the test setup
jest.mock("../components/InventoryCard", () => ({ id, title }) => (
    <div data-testid={`inventory-item-${id}`}>{title}</div>
  ));
  

/**
 * Test suite for the HomePage component.
 * 
 * @group HomePage
 */
describe('HomePage', () => {
  /**
   * Test case to verify that the HomePage component displays inventory items correctly.
   * 
   * @test {HomePage} Displays inventory items
   */
  it('displays inventory items', () => {
    render(<HomePage />);
    const inventoryItems = screen.getAllByTestId(/inventory-item-/i);
    expect(inventoryItems).toHaveLength(6); 
  });

  /**
   * Test case to verify that a new inventory item is added when the add button is clicked.
   * 
   * @test {HomePage} Adds a new inventory item when add button is clicked
   */
  it('adds a new inventory item when add button is clicked', () => {
    render(<HomePage />);
    const addButton = screen.getByRole('button', { name: /add inventory item/i });
    fireEvent.click(addButton);

    const inventoryItems = screen.getAllByTestId(/inventory-item-/i);
    expect(inventoryItems).toHaveLength(7); 
  });

});
