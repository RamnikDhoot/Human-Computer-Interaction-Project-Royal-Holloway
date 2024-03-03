import React from 'react';
import { render } from '@testing-library/react';

import '@testing-library/jest-dom';
import Dashboard from '../pages/Dashboard'; 

describe('Dashboard', () => {
  it('renders without crashing', () => {
    render(<Dashboard />);
    expect(true).toBeTruthy();
  });
});
