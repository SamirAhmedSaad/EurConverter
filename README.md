# Euro Converter Task
> Requirments <br/>
>> Use EUR as base currency (default argument in network and repository and client can change it) <br/>
>> Hint for mandatory base currency name on top of screen <br/>
>> Display list of currency rates based on EUR as (placeholder image, name and rate) <br/>
>> Only base currency edit text should be editable <br/>
>> Use fixer.io as a currency provider <br/>
>> Design to support English and Arabic <br/>
>> Resources to support English and Arabic (based on device language) <br/>
>> Use MVVM,Koltin,Coroutines and a default behavior for English/Arabic language<br/>
>> Mapper from Api resonse to ViewData  to ensure consistency (CurrencyRateViewData) <br/>

#####Notes : 
1. Fixer.io almost broken or very slow all the time. I was able to hardly read the documentation after a few times
2. Default time for Fixer is GMT+00 ( based on no.1 I can't easily find if there is time zone should i send to return based on it and of course i didn't handle it locally in this task )
3. App Will get list of Currency Exchange Rates and proceed with them locally means by that no request to Fixer api will fire to convert it in a real time
4. I Built this from scratch as I said in the interview I'm now using  MVI so forgive me if there is any missing or untest layer.
5.Api Url and API_KEY saved in gradle in unsecure way of course in real project you should save them using a secured technique like Ndk  ( .so file ) to add more difficult layer when decompiling or if the project is small enough you can depend on proguard obfuscations only. 
6. Finally, I Implemented design to be close to attached images
