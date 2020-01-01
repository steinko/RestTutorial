import getDriver from "./setupTests"
import {By} from 'selenium-webdriver'
let driver

beforeEach(() => {
   driver = getDriver()
 })

 describe('get person', () => {

   it ('should be id 2', async () => { 
	 const value = await driver.findElement(By.id('id')).getAttribute('value')
	 expect(value).toBe('2')
   })

   it ('should be first name  Stein',async () => { 
	 const value = await driver.findElement(By.id('firstName')).getAttribute('value')
	 expect(value).toBe('Stein')
   })

   it ('should be familiy name  Korsveien', async () => { 
	 const value = await driver.findElement(By.id('familyName')).getAttribute('value')
	 expect(value).toBe('Korsveien')
   })

 })

 describe('create person', () => {



   it ('should be first name  Sturlar',async () => { 
	 const value = await driver.findElement(By.id('firstName'))
	 await value.clear()
	 await value.sendKeys('Sturlar')
	 expect(await value.getAttribute('value')).toBe('Sturlar')
   })

   it ('should be familiy name  Sturlarson', async () => { 
	 const value = await driver.findElement(By.id('familyName'))
	 await value.clear()
	 await value.sendKeys('Sturlarson')
	 expect(await value.getAttribute('value')).toBe('Sturlarson')
   })

   it ('should be familiy name  Sturlarson', async () => { 
	 const value = await driver.findElement(By.id('submit'))
	 await value.click()
	 const firstName = await driver.findElement(By.id('firstName'))
	 expect(await firstName.getAttribute('value')).toBe('Sturlar')
	 const familyName = await driver.findElement(By.id('familyName'))
	 expect(await familyName.getAttribute('value')).toBe('Sturlarson')
   })



 })

