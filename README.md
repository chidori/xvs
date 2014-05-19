# XML Validation Server

Simple web server that validates XML via XSD v1.1.
P.S. With dummy client side :#

## Usage

Application using [xml schema definition language (XSD) v1.1](http://www.w3.org/TR/xmlschema11-1/) to validate XML
via [Apache Xerces2 Java library](http://xerces.apache.org/xerces2-j/).

Only beta version of xerces2-j library can validate XSD v1.1 so it can`t be found in maven repository.

To resolve this problem you should add libraries from the `lib/` folder to your local maven repository
with help of [lein-localrepo](https://github.com/kumarshantanu/lein-localrepo) plugin.

After that you can run server with
```code
lein uberjar
java -jar target/xvs-0.1.0-SNAPSHOT-standalone.jar
```
and see result on [http://localhost:9090](http://localhost:9090)
