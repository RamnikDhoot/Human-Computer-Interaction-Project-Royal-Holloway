import '@testing-library/jest-dom';
import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Dashboard from '../pages/Dashboard';

// Mocking the canvas element which Chart.js uses
jest.mock('react-chartjs-2', () => ({
  Line: () => null,
  Bar: () => null,
}));

/**
 * Test suite for the Dashboard component.
 * 
 * @group Dashboard
 */
describe('Dashboard', () => {
  /**
   * Test case to verify adding a task.
   * 
   * @test {Dashboard} Allows users to add a task
   */
  it('allows users to add a task', async () => {
    render(<Dashboard />);

    const inputElement = screen.getByPlaceholderText('Add new task...');
    const addButton = await screen.findByRole('button', { name: 'Add Task' });

    fireEvent.change(inputElement, { target: { value: 'New Task' } });
    fireEvent.click(addButton);

    const newTask = await screen.findByText('New Task');
    expect(newTask).toBeInTheDocument();
  });

  /**
   * Test case to verify clearing input after adding a task.
   * 
   * @test {Dashboard} Clears input after adding a task
   */
  it('clears input after adding a task', async () => {
    render(<Dashboard />);
  
    const inputElement = screen.getByPlaceholderText('Add new task...');
    fireEvent.change(inputElement, { target: { value: 'New Task' } });
    fireEvent.click(screen.getByRole('button', { name: 'Add Task' }));
  
    await screen.findByText('New Task');
    expect(inputElement.value).toBe('');
  });
  
  /**
   * Test case to verify toggling task completion state.
   * 
   * @test {Dashboard} Toggles task completion state
   */
  it('toggles task completion state', async () => {
    render(<Dashboard />);
  
    const inputElement = screen.getByPlaceholderText('Add new task...');
    fireEvent.change(inputElement, { target: { value: 'New Task' } });
    fireEvent.click(screen.getByRole('button', { name: 'Add Task' }));
  
    const taskItem = await screen.findByText('New Task');
    fireEvent.click(taskItem); // Toggle task to completed
  
    expect(taskItem).toHaveClass('list-group-item-secondary'); 
  });
  
  /**
   * Test case to verify removing a task on double click.
   * 
   * @test {Dashboard} Removes a task on double click
   */
  it('removes a task on double click', async () => {
    render(<Dashboard />);
  
    const inputElement = screen.getByPlaceholderText('Add new task...');
    fireEvent.change(inputElement, { target: { value: 'New Task' } });
    fireEvent.click(screen.getByRole('button', { name: 'Add Task' }));
  
    const taskItem = await screen.findByText('New Task');
    fireEvent.doubleClick(taskItem); // Remove task on double click
  
    expect(screen.queryByText('New Task')).not.toBeInTheDocument();
  });
  
  /**
   * Test case to verify not adding an empty task.
   * 
   * @test {Dashboard} Does not add an empty task
   */
  it('does not add an empty task', () => {
    render(<Dashboard />);
  
    fireEvent.click(screen.getByRole('button', { name: 'Add Task' }));
    const tasks = screen.queryAllByRole('listitem');
    expect(tasks.length).toBe(0);
  });
});
