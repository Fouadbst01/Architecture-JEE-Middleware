module.exports = {
  darkMode:'class',
  content: ['./src/**/*.{html,ts}', './projects/**/*.{html,ts}'],
  theme: {
    extend: {
      height: {
        '9/10': '90%',
        '8/10': '80%',
        '1/10': '10%',
      },
      width:{
        '48p' : '49%',
        '50rem': '25rem'
      },
      colors:{
        'green': '#78C84E',
        'darkBack': '#181A1B',
        'darkBack2': '#303436',
        'darkBack3':'#1b1d1e',
        'darkText': '#9e9589',
        'lightText': '#6b7479',
        'lightBack': '#D0D7D9'
      }
    },
  },
  plugins: [],
}
