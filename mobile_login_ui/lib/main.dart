import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
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
  Widget iconVisibilityPassword = Icon(
    Icons.visibility_off_outlined,
    color: Colors.blue,
  );
  bool isVisibility = false;

  void _showPassword() {
    setState(() {
      if (isVisibility) {
        iconVisibilityPassword = Icon(
          Icons.visibility_off_outlined,
          color: Colors.blue,
        );
        isVisibility = false;
      } else {
        iconVisibilityPassword = Icon(
          Icons.visibility_outlined,
          color: Colors.blue,
        );
        isVisibility = true;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Stack(
          children: <Widget>[
            Container(
              width: MediaQuery.of(context).size.width,
              decoration: BoxDecoration(
                image: DecorationImage(
                  image: AssetImage("assets/images/background-login.jpg"),
                  fit: BoxFit.cover,
                ),
              ),
            ),
            Container(
              width: MediaQuery.of(context).size.width,
              color: const Color(0xFF404886).withOpacity(0.8),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Container(
                    child: Text(
                      'Member Sign In',
                      style: TextStyle(
                          fontSize: 20.0,
                          fontWeight: FontWeight.bold,
                          color: Colors.white),
                    ),
                  ),
                  Container(
                    margin: EdgeInsets.all(20.0),
                    child: TextField(
                      decoration: InputDecoration(
                        hintText: 'email@example.com',
                        prefixIcon: Icon(
                          Icons.person_outlined,
                          color: Colors.white,
                        ),
                        hintStyle: TextStyle(
                          color: Colors.grey,
                        ),
                        border: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.black54),
                        ),
                      ),
                    ),
                  ),
                  Container(
                    margin: EdgeInsets.all(20.0),
                    child: TextField(
                      decoration: InputDecoration(
                        hintText: 'password',
                        prefixIcon: Icon(
                          Icons.https_outlined,
                          color: Colors.white,
                        ),
                        suffixIcon: IconButton(
                          icon: iconVisibilityPassword,
                          onPressed: _showPassword,
                        ),
                        hintStyle: TextStyle(
                          color: Colors.grey,
                        ),
                        border: UnderlineInputBorder(
                          borderSide: BorderSide(color: Colors.black54),
                        ),
                      ),
                    ),
                  ),
                  Container(
                    margin: EdgeInsets.all(20.0),
                    width: MediaQuery.of(context).size.width,
                    height: 50,
                    child: ElevatedButton(
                      child: Text(
                        'Sign In',
                        style: TextStyle(color: Colors.white, fontSize: 16.0),
                      ),
                      style: ButtonStyle(
                        backgroundColor: MaterialStateProperty.all<Color>(
                          const Color(0xFF19A3D4),
                        ),
                      ),
                      onPressed: null,
                    ),
                  ),
                  Container(
                    height: 50,
                    color: const Color(0xFF2E60D3),
                    margin: EdgeInsets.all(20.0),
                    child: Row(
                      children: <Widget>[
                        Expanded(
                          flex: 1,
                          child: Icon(
                            Icons.face,
                            color: Colors.white,
                          ),
                        ),
                        Expanded(
                          flex: 2,
                          child: Text(
                            'Sign In Using Apple',
                            style:
                                TextStyle(color: Colors.white, fontSize: 16.0),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
