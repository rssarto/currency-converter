export class AppSettings {
  authenticationUrl = 'http://localhost:8080/currency-converter/token/generate-token';
  logoutUrl = 'http://localhost:8080/currency-converter/logout';
  signUpUrl = 'http://localhost:8080/currency-converter/signup';

  currencyListUrl = 'http://localhost:8080/currency-converter/api/listAll';
  quotationUrl = 'http://localhost:8080/currency-converter/api/quote';
  historicUrl = 'http://localhost:8080/currency-converter/api/historic';
}
