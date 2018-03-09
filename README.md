# Getting started

It's a simple application that creating data and returning it throught REST API.

# For launch

## application of options
  - you can apply options by default:
  - you can custom options;

### Options by default
- if options by default when using DaoMock.
- Creating and store data into application.
- length of data = 10000
- host of rest api - localhost
- port of rest api - 9080

### Custom options
you should apply the options to argument of app in format
`<nameOption1>=<value> <nameOption2=<value>`

#### supported options
- `storage=<String>` (mock or Mysql) where will be store of data. Default - `mock`
- `dataLength=<Integer>` - number of raw. Default - `10000`
- `rest.host=<String>` - host of rest api. Default - `localhost`
- `rest.port=<Integer>` - port of rest api. Default - `9080`
- `mysql.host=<String>` - host of mysql server. Default - `localhost`
- `mysql.port=<Integer>` - port of mysql server. Default - `3306`
- `mysql.databaseName=<String>` - the name of database. You should use existing database. Default - `tinkoff`
- `mysql.autoReconnect=<Boolean>`  Default - `true`
- `mysql.useSSL=<Boolean>` Default - `false`
- `mysql.useJDBCCompliantTimezoneShift=<Boolean>` Default - `true`
- `mysql.useLegacyDatetimeCode=<Boolean>` Default - `false`
- `mysql.serverTimezone=<String>` Default - `UTC`
- `mysql.driver=<String>` Default = `com.mysql.cj.jdbc.Driver`
- `mysql.username=<String>` username for connect to Mysql server. Default - `root`
- `mysql.password=<String>` password for connect to Mysql server. Default - `root`



## Using REST API
`REST API` supports two url
- `GET http://<host>:<port>/tinkoff/data.getData` - returns all data from the storage
- `GET http://<host>:<port>/tinkoff/data.getBackData` - returns only data that insert by back time