import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Footer from '../components/Footer';

/**
 * Test suite for the Footer component.
 * 
 * @group Footer
 */
describe('Footer Component', () => {
  beforeEach(() => {
    render(<Footer />);
  });

  /**
   * Test case to verify rendering of the footer.
   * 
   * @test {Footer} Renders the footer
   */
  it('renders the footer', () => {
    const footerElement = screen.getByRole('contentinfo');
    expect(footerElement).toBeInTheDocument();
  });

  /**
   * Test case to verify presence of the copyright notice.
   * 
   * @test {Footer} Contains the copyright notice
   */
  it('contains the copyright notice', () => {
    expect(screen.getByText(/2024 Company Name. All rights reserved./i)).toBeInTheDocument();
  });

  /**
   * Test case to verify presence of navigation links to about, contact, and privacy policy.
   * 
   * @test {Footer} Contains navigation links to about, contact, and privacy policy
   */
  it('contains navigation links to about, contact, and privacy policy', () => {
    expect(screen.getByText('About Us').closest('a')).toHaveAttribute('href', '/about');
    expect(screen.getByText('Contact').closest('a')).toHaveAttribute('href', '/contact');
    expect(screen.getByText('Privacy Policy').closest('a')).toHaveAttribute('href', '/privacy');
  });

  /**
   * Test case to verify correctness of text for the navigation links.
   * 
   * @test {Footer} Has correct text for the navigation links
   */
  it('has correct text for the navigation links', () => {
    const links = screen.getAllByRole('link');
    expect(links).toHaveLength(3); 
    expect(links[0]).toHaveTextContent('About Us');
    expect(links[1]).toHaveTextContent('Contact');
    expect(links[2]).toHaveTextContent('Privacy Policy');
  });
});
