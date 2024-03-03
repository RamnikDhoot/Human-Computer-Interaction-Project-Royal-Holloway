import '@testing-library/jest-dom';
import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import Dashboard from '../pages/Dashboard';

// Mocking the canvas element which Chart.js uses
jest.mock('react-chartjs-2', () => ({
  Line: () => null,
  Bar: () => null,
}));

describe('Dashboard', () => {
  it('allows users to add a task', async () => {
    render(<Dashboard />);

    const inputElement = screen.getByPlaceholderText('Add new task...');
    const addButton = await screen.findByRole('button', { name: 'Add Task' });

    fireEvent.change(inputElement, { target: { value: 'New Task' } });
    fireEvent.click(addButton);

    const newTask = await screen.findByText('New Task');
    expect(newTask).toBeInTheDocument();
  });

  it('clears input after adding a task', async () => {
    render(<Dashboard />);
  
    const inputElement = screen.getByPlaceholderText('Add new task...');
    fireEvent.change(inputElement, { target: { value: 'New Task' } });
    fireEvent.click(screen.getByRole('button', { name: 'Add Task' }));
  
    await screen.findByText('New Task');
    expect(inputElement.value).toBe('');
  });
  
  it('toggles task completion state', async () => {
    render(<Dashboard />);
  
    const inputElement = screen.getByPlaceholderText('Add new task...');
    fireEvent.change(inputElement, { target: { value: 'New Task' } });
    fireEvent.click(screen.getByRole('button', { name: 'Add Task' }));
  
    const taskItem = await screen.findByText('New Task');
    fireEvent.click(taskItem); // Toggle task to completed
  
    expect(taskItem).toHaveClass('list-group-item-secondary'); 
  });
  
  it('removes a task on double click', async () => {
    render(<Dashboard />);
  
    const inputElement = screen.getByPlaceholderText('Add new task...');
    fireEvent.change(inputElement, { target: { value: 'New Task' } });
    fireEvent.click(screen.getByRole('button', { name: 'Add Task' }));
  
    const taskItem = await screen.findByText('New Task');
    fireEvent.doubleClick(taskItem); // Remove task on double click
  
    expect(screen.queryByText('New Task')).not.toBeInTheDocument();
  });
  
  it('does not add an empty task', () => {
    render(<Dashboard />);
  
    fireEvent.click(screen.getByRole('button', { name: 'Add Task' }));
    const tasks = screen.queryAllByRole('listitem');
        expect(tasks.length).toBe(0);
  });
  
});
