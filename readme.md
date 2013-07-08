###Aclys

A tiny experimental in-memory key/value store on top of Finagle. 

------------
####Notes 

I've been immersed with Scala at the where I work, this is a small experiment I embarked myself into in order to understand some key concepts in Scala:

- An experiment on top of Finagle, accessed through HTTP (GET, POST, DELETE. Refer to Usage section for more info)
- It's something "Redis-like" for thick Javascript Apps
- All in a single file under 90 lines of code
- Not meant for Production. Doesn't even have authentication. Not as fast as Redis or Memcache (100k read/write HTTP requests with 5 concurrent clients are around 1450-1510 requests per second over localhost)
- "So I can access it on HTTP? Do I need a web server?". Aclys is built on top of Finagle, no web server needed. Setting it up to run on Heroku is [easy](https://devcenter.heroku.com/articles/scala). Setting it up to run on your computer is easy as well, clone the repo, install **sbt** and run: **sbt run** in the base directory.

There's only one method in Aclys

	/?key=

This method is A RESTful interface:

	GET		Returns the value based on the Key
	DELETE	Deletes the value based on the Key
	POST	Creates a value based on the Key, the value should be passed through the Request Body (value not in JSON)

If the key doesn't exist or is not provided, Aclys will return a 404.

> Aclys (Latin) - a **small** javelin

####License

Copyright (c) 2012, Ace Subido
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

####Author

I'm [Ace Subido](http://acesubido.com), (UI/UX) Founding Team Member at Orchestrack.
