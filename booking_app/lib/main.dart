import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.

  static const String appTitle = 'Booking App';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: appTitle,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Booking App'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyStateWidget();
}

class _MyStateWidget extends State<MyHomePage> {
  static const TextStyle styleOption =
      TextStyle(fontSize: 30.0, fontWeight: FontWeight.bold);

  int _selectedIndex = 0;
  static int _selectedIndexCategories = 0;
  static const List<String> _categories = <String>[
    'Trips',
    'Hotels',
    'Mountains',
    'Beachs'
  ];
  static const List<String> _listLabel = ['.', '.', '.', '.'];
  final List<String> _listTripName = [
    'Nusa Pedina',
    'Kuta Beach',
    'Indrayanti Beach',
    'Parangtritis Beach'
  ];
  final List<String> _listLocationTrip = [
    'Bali, Indonesia',
    'Bali Indonesia',
    'Yogyakarta, Indonesia',
    'Yogyakarta, Indonesia'
  ];
  final List<String> _listImageName = [
    'Trip 1.jpg',
    'Trip 2.jpg',
    'Trip 3.jpg',
    'Trip 4.jpg'
  ];

  List<Widget> _historyWidgets = <Widget>[
    Padding(
      padding: EdgeInsets.fromLTRB(10.0, 30.0, 10.0, 10.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Column(children: [
            Text('History', style: styleOption),
          ]),
        ],
      ),
    ),
  ];

  List<Widget> _favoriteWidgets = <Widget>[
    Padding(
      padding: EdgeInsets.fromLTRB(10.0, 30.0, 10.0, 10.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Column(children: [
            Text('Favorite', style: styleOption),
          ]),
        ],
      ),
    ),
  ];

  List<Widget> _accountWidgets = <Widget>[
    Padding(
      padding: EdgeInsets.fromLTRB(10.0, 30.0, 10.0, 10.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Column(children: [
            Text('Account Settings', style: styleOption),
          ]),
        ],
      ),
    ),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  List<Widget> _mainWidgets() {
    if (_selectedIndex == 0) {
      return <Widget>[
        Padding(
          padding: EdgeInsets.fromLTRB(10.0, 30.0, 10.0, 30.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'Hi John,',
                style: TextStyle(fontSize: 16.0),
                textAlign: TextAlign.left,
              ),
              Text('Where do you wanna go?', style: styleOption),
            ],
          ),
        ),
        Container(
          padding: EdgeInsets.all(10.0),
          child: TextField(
            decoration: InputDecoration(
                prefixIcon: Icon(Icons.search),
                enabledBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Colors.white),
                  borderRadius: BorderRadius.circular(15.0),
                ),
                filled: true,
                fillColor: Colors.white,
                hintText: 'Where do you want to go?'),
          ),
        ),
        Container(
            margin: EdgeInsets.fromLTRB(10.0, 0, 0, 0),
            width: double.infinity,
            child: Text('Categories',
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 14))),
        Container(
          height: 60,
          margin: EdgeInsets.fromLTRB(10.0, 20.0, 20.0, 0),
          child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: _categories.length,
              itemBuilder: (context, index) {
                return InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    padding: EdgeInsets.all(15.0),
                    margin: EdgeInsets.fromLTRB(0, 0, 10.0, 0),
                    width: 120,
                    decoration: BoxDecoration(
                        color: (index == _selectedIndexCategories)
                            ? const Color(0xFFEE684A)
                            : Colors.white,
                        borderRadius: BorderRadius.circular(20.0)),
                    child: Text(_categories.elementAt(index),
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            color: (index == _selectedIndexCategories)
                                ? Colors.white
                                : Colors.black,
                            fontWeight: FontWeight.bold)),
                  ),
                  onTap: () {
                    setState(() {
                      _selectedIndexCategories = index;
                    });
                  },
                );
              }),
        ),
        Container(
          margin: EdgeInsets.fromLTRB(10.0, 20.0, 0, 0),
          width: double.infinity,
          child: Text(
            'Top trips',
            style: TextStyle(fontWeight: FontWeight.bold, fontSize: 14),
          ),
        ),
        ConstrainedBox(
          constraints: BoxConstraints(maxHeight: 300),
          child: Padding(
            padding: EdgeInsets.all(10.0),
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: 4,
              itemBuilder: (context, index) {
                return Container(
                  margin: EdgeInsets.fromLTRB(0, 0, 20.0, 0),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(15.0),
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Container(
                        margin: EdgeInsets.all(15.0),
                        width: 250,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(15.0),
                        ),
                        child: Card(
                          child: ClipRRect(
                            child: Image(
                              image: AssetImage(
                                  'assets/images/${_listImageName.elementAt(index)}'),
                              fit: BoxFit.fill,
                              height: 150,
                            ),
                            borderRadius: BorderRadius.circular(15.0),
                          ),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(15.0),
                          ),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.fromLTRB(20.0, 15.0, 0, 5.0),
                        child: Text(
                          _listTripName.elementAt(index),
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 18.0,
                          ),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.symmetric(horizontal: 20.0),
                        child: Text(
                          _listLocationTrip.elementAt(index),
                        ),
                      ),
                    ],
                  ),
                );
              },
            ),
          ),
        ),
      ];
    } else if (_selectedIndex == 1) {
      return _historyWidgets;
    } else if (_selectedIndex == 2) {
      return _favoriteWidgets;
    } else if (_selectedIndex == 3) {
      return _accountWidgets;
    }
    return <Widget>[];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: Text(
          widget.title,
          style: TextStyle(color: Colors.black),
        ),
        leading: Builder(
          builder: (context) => IconButton(
              icon: SvgPicture.asset(
                "assets/icon/drawer.svg",
                height: 15,
                width: 34,
              ),
              onPressed: () => Scaffold.of(context).openDrawer()),
        ),
        actions: [
          IconButton(
            icon: Icon(
              Icons.people,
              color: Colors.black,
            ),
            tooltip: 'User',
            onPressed: () {
              // handle the press
            },
          )
        ],
        backgroundColor: Colors.white,
        elevation: 0,
        titleSpacing: 0,
        centerTitle: true,
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: _mainWidgets(),
        ),
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: [
            const DrawerHeader(
                decoration: BoxDecoration(color: Colors.blue),
                child: Text('Header')),
            ListTile(
              title: Text('Item 1'),
              onTap: () {
                Navigator.pop(context);
              },
            ),
            ListTile(
                title: Text('Item 2'),
                onTap: () {
                  Navigator.pop(context);
                })
          ],
        ),
      ),
      bottomNavigationBar: Container(
        height: 80,
        margin: EdgeInsets.symmetric(vertical: 10.0, horizontal: 10.0),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20.0),
        ),
        child: ClipRRect(
          borderRadius: BorderRadius.circular(20.0),
          child: BottomNavigationBar(
              items: <BottomNavigationBarItem>[
                BottomNavigationBarItem(
                  icon: Icon(Icons.home),
                  label: _listLabel.elementAt(_selectedIndex),
                  backgroundColor: Colors.black,
                  tooltip: _listLabel.elementAt(_selectedIndex),
                ),
                BottomNavigationBarItem(
                  icon: Icon(Icons.history),
                  label: _listLabel.elementAt(_selectedIndex),
                  backgroundColor: Colors.black,
                  tooltip: _listLabel.elementAt(_selectedIndex),
                ),
                BottomNavigationBarItem(
                  icon: Icon(Icons.favorite),
                  label: _listLabel.elementAt(_selectedIndex),
                  backgroundColor: Colors.black,
                  tooltip: _listLabel.elementAt(_selectedIndex),
                ),
                BottomNavigationBarItem(
                  icon: Icon(Icons.people),
                  label: _listLabel.elementAt(_selectedIndex),
                  backgroundColor: Colors.black,
                  tooltip: _listLabel.elementAt(_selectedIndex),
                )
              ],
              currentIndex: _selectedIndex,
              selectedItemColor: Colors.white,
              onTap: _onItemTapped),
        ),
      ),
      backgroundColor: const Color(0xFFF2F2F2),
    );
  }
}
