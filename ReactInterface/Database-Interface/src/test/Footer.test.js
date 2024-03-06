import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Footer from '../components/Footer';

describe('Footer Component', () => {
  beforeEach(() => {
    render(<Footer />);
  });

  it('renders the footer', () => {
    const footerElement = screen.getByRole('contentinfo');
    expect(footerElement).toBeInTheDocument();
  });

  it('contains the copyright notice', () => {
    expect(screen.getByText(/2024 Company Name. All rights reserved./i)).toBeInTheDocument();
  });

  it('contains navigation links to about, contact, and privacy policy', () => {
    expect(screen.getByText('About Us').closest('a')).toHaveAttribute('href', '/about');
    expect(screen.getByText('Contact').closest('a')).toHaveAttribute('href', '/contact');
    expect(screen.getByText('Privacy Policy').closest('a')).toHaveAttribute('href', '/privacy');
  });

  it('has correct text for the navigation links', () => {
    const links = screen.getAllByRole('link');
    expect(links).toHaveLength(3); 
    expect(links[0]).toHaveTextContent('About Us');
    expect(links[1]).toHaveTextContent('Contact');
    expect(links[2]).toHaveTextContent('Privacy Policy');
  });
});
