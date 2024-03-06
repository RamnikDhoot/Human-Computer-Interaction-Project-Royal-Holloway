
module.exports = {
  transform: {
    '^.+\\.jsx?$': 'babel-jest', // Transform JavaScript and JSX files with babel-jest
  },
  moduleNameMapper: {
    '\\.(css|less|scss|sass)$': 'identity-obj-proxy' // Mock CSS modules
  },
  testEnvironment: 'jest-environment-jsdom',
  setupFiles: ['jest-canvas-mock'],
  moduleDirectories: ['node_modules', 'src'],
  setupFilesAfterEnv: ['@testing-library/jest-dom'],


};
``