let chrome = require('selenium-webdriver/chrome');
let {Builder} = require('selenium-webdriver');

const hostName = 'http://localhost:3000'
let driver

beforeAll(async ()=>{  
   jest.setTimeout(100000)
    driver = await new Builder()
     .forBrowser('chrome')
     .setChromeOptions(new chrome.Options().headless())
     .build();
 })

afterAll(async ()=>{ 
   await driver.quit()
 })

it('should do nothing', ()=> { })

export default function getDriver() { 
    return driver}
    
export function getHostName() { 
   return hostName
 }
