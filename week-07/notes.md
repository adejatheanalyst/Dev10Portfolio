HTTP is a request/response protocol
HTTP 1.1 REQUEST
[request method] [path] [protocol version]
[header name]: [header value]*
[blank line]

The REQUEST METHOD has nine common values. Some of the methods correspond to CRUD operations. A browser with JavaScript disabled can only use two of them: GET and POST.

**GET**
A read operation. Returns data, HTML, CSS or the like.
POST
An insert or create operation.
PUT
An update operation.
DELETE
A delete operation.
HEAD
The identical response as GET but with no response body (HTML, CSS, data). Useful for troubleshooting HTTP response codes and headers.
OPTIONS
Returns HTTP methods that are supported for the given path.
PATCH
A partial update.
CONNECT
Used to escalate from HTTP to HTTPS. HTTPS encrypts requests and responses before they travel through the network.
TRACE
Echos the request the server received. Useful to check how intermediate network nodes modify the original request.
**LET**



The PATH is either an absolute or relative URL. If the path is relative, the Host header is required and the path is appended to the Host value to form a complete URL.
The PROTOCAL VERSION indicates which version the client prefers to use. The server is free to ignore it. It may return a response encoded with a different version. Then it's the client's responsibility to decide what to do with it. HTTP/1.1 is the most common HTTP version today. If HTTP/2.0 is requested and the server supports it, both client and server switch to the 2.0 binary protocol.

HTTP 1.1 REPSONSE:
[protocol version] [status code] [status name]
[header name]: [header value]*
[blank line]
[response body]
[blank line]

The PROTOCAL VERSION is the HTTP protocol of the response. Regardless of what the client requested, this is the protocol the server used.
An HTTP STATUS CODE is a three-digit number between 100 and 599. Each set of one hundred is categorized with specific intent.
100s: Informational. Neither success or failure.
200s: Success.
300s: Redirection. The request hasn't failed, but the client must use a different approach.
400s: Your fault. The problem is on the client. The request failed because of something the client did.
500s: My fault. The problem is on the server. The request failed because of something the server did.

The STATUS NAME is short text describing the status code.

Status	Description
200 OK	Everything worked, hurray.
301 Moved Permanently	I understand what you're asking, but the URL changed. Use this new URL.
404 Not Found	I don't understand that URL. I don't respond to that URL.
405 Method Not Allowed	I understand that URL, but I don't respond to that method.
500 Internal Server Error	It looks like your request is fine, but something went wrong on my end. The application that handles the request crashed.



**PROTOCOL**
A formal communication language. We've seen HTTP. Other protocols include:
*https:* Secure HTTP
*ftp:* File Transfer Protocol
*smtp:* Simple Mail Transfer Protocol
*file: *Access a file on our local machine.
**Domain**
A formally registered name that maps to an IP address.
**Port**
A port isn't something physical in a network card. It's a "logical" number that further routes network traffic.

If a street address is a domain, an apartment number is a port. If a book store is a domain, a genre section is a port. If a grocery store is a domain, an aisle is a port. Note that we can't find a specific resource with a domain and port. That's the job of the *path.*
**Path**
A domain and port narrow down to an IP and a process. A path addresses a specific resource within that process. In the past, a path mapped to directories and files. That's not as common today. Again, a path is logical, not physical.
**Query String**
An optional URL segment. The query string starts with the ? symbol and may include key/value pairs or just a value. Useful for further refining a specific resource.
**Fragment**
An optional URL segment. A fragment starts with the # symbol. An HTTP server ignores it. It's only useful in the client.


The acronym **"REST"** stands for Representational State Transfer. REST is a set of software architecture guidelines or constraints. It isn't a formal specification. Roy Fielding defined REST in his PhD thesis while working on the HTTP/1.1 spec.