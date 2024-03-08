import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import InventoryCard from '../components/InventoryCard';

// Mock the coverImage import
jest.mock('../assets/images/cover.jpg', () => 'dummy-image-url');
// Mock the alert function
window.alert = jest.fn();

/**
 * Test suite for the InventoryCard component.
 * 
 * @group InventoryCard
 */
describe('InventoryCard Component', () => {
  /**
   * Test case to verify that the InventoryCard component renders correctly.
   * 
   * @test {InventoryCard} Renders correctly
   */
  test('renders correctly', () => {
    const { getByText, getByPlaceholderText } = render(
      <InventoryCard id={1} title="Test Inventory" description="Test Description" />
    );

    expect(getByText('Test Inventory')).toBeInTheDocument();
    expect(getByText('Test Description')).toBeInTheDocument();
    expect(getByPlaceholderText('Enter new name')).toBeInTheDocument();
  });

  /**
   * Test case to verify that the name changes when the input is changed and the button is clicked.
   * 
   * @test {InventoryCard} Changes name when input is changed and button is clicked
   */
  test('changes name when input is changed and button is clicked', () => {
    const { getByPlaceholderText, getByText } = render(
      <InventoryCard id={1} title="Test Inventory" description="Test Description" />
    );

    const nameInput = getByPlaceholderText('Enter new name');
    fireEvent.change(nameInput, { target: { value: 'New Inventory Name' } });

    fireEvent.click(getByText('Change Name'));

    expect(window.alert).toHaveBeenCalledWith('Changing name for Inventory 1 to: New Inventory Name');
  });

  /**
   * Test case to verify that the photo changes when the file input is changed and the button is clicked.
   * 
   * @test {InventoryCard} Changes photo when file input is changed and button is clicked
   */
  test('changes photo when file input is changed and button is clicked', () => {
    const { getByLabelText, getByText } = render(
      <InventoryCard id={1} title="Test Inventory" description="Test Description" />
    );

    const fileInput = getByLabelText('New Photo:');
    fireEvent.change(fileInput, { target: { files: [new File([], 'dummy-image.jpg', { type: 'image/jpeg' })] } });

    fireEvent.click(getByText('Change Photo'));

    expect(window.alert).toHaveBeenCalledWith('Changing photo for Inventory 1');
  });
});
