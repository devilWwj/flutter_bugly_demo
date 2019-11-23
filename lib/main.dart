import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';



const platform = const MethodChannel('com.hc.flutter');


void collectLog(String line){
  //收集日志
}
void reportErrorAndLog(FlutterErrorDetails details){
 //上报错误和日志逻辑
  platform.invokeMethod('report',details.toString());

}

FlutterErrorDetails makeDetails(Object obj, StackTrace stack){
 // 构建错误信息
}

void main() {

  FlutterError.onError = (FlutterErrorDetails details) {
    reportErrorAndLog(details);
  };

  runZoned(
        () => runApp(MyApp()),
    zoneSpecification: ZoneSpecification(

      print: (Zone self, ZoneDelegate parent, Zone zone, String line) {
        collectLog(line); //手机日志
      },

    ),
    onError: (Object obj, StackTrace stack) {
      var details = makeDetails(obj, stack);
      reportErrorAndLog(details);
    },
  );
}




//void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(

        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(

          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[


            RaisedButton(
              child: Text("手动上报测试了"),
              onPressed: (){
                platform.invokeMethod('report',"手动上报错误");
              },
            ),


            RaisedButton(
              child: Text("修复之后 toast"),
              onPressed: (){
                platform.invokeMethod('nativePage');
              },
            ),



          ],
        ),
      ),


      // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
