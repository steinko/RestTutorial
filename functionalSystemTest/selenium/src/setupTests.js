// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom

let driver
let chrome = require('selenium-webdriver/chrome');
let {Builder} = require('selenium-webdriver');

beforeAll(async ()=>{  
   jest.setTimeout(100000)
    driver = await new Builder()
     .forBrowser('chrome')
     .setChromeOptions(new chrome.Options().headless())
     .build();
      const url = 'http://localhost:3000/'
	   await driver.get(url)
 })

afterAll(async ()=>{ 
   await driver.quit()
 })

 it('should do nothing', ()=> { })

 export default function getDriver() { 
	 return driver}
