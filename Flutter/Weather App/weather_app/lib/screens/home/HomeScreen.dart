import 'package:flutter/material.dart';
import 'package:weather_app/screens/home/tabs/Home%20Tab/HomeTab.dart';
import 'package:weather_app/screens/home/tabs/Search%20Tab/SearchTab.dart';
import 'package:weather_app/screens/home/tabs/User%20Tab/UserTab.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _selectedIndex = 0;

  static const List<Widget> _widgetOptions = <Widget>[
    HomeTab(),
    SearchTab(),
    UserTab(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      extendBody: true,
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: ClipRRect(
        borderRadius: const BorderRadius.only(
          topLeft: Radius.circular(8.0),
          topRight: Radius.circular(8.0),
        ),
        child: BottomNavigationBar(
          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              icon: Icon(Icons.home_outlined),
              label: '•',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.search_outlined),
              label: '•',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.person_2_outlined),
              label: '•',
            ),
          ],
          backgroundColor: const Color.fromARGB(255, 16, 31, 55),
          currentIndex: _selectedIndex,
          selectedItemColor: Colors.orange,
          unselectedItemColor: Colors.white,
          onTap: _onItemTapped,
        ),
      ),
    );
  }
}
