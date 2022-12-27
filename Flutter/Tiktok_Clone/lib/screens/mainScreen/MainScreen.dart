import 'package:flutter/material.dart';
import 'package:tiktok_clone/screens/mainScreen/home/HomeTabScreen.dart';
import 'package:tiktok_clone/screens/mainScreen/inbox/InboxTabScreen.dart';
import 'package:tiktok_clone/screens/mainScreen/profile/ProfileTabScreen.dart';

import 'add/AddTabScreen.dart';
import 'discover/DiscoverTabScreen.dart';

class MainScreen extends StatefulWidget {
  static const routeName = '/main_screen';
  const MainScreen({Key? key}) : super(key: key);

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int _selectedIndex = 0;

  static const List<Widget> _pageTabOption = <Widget>[
    HomeTabScreen(),
    DiscoverTabScreen(),
    AddTabScreen(),
    InboxTabScreen(),
    ProfileTabScreen()
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
          color: Color.fromRGBO(22, 24, 35, 1)
        ),
        width: double.infinity,
        child: _pageTabOption.elementAt(_selectedIndex)
      ),
      bottomNavigationBar: Container(
        color: const Color.fromRGBO(22, 24, 35, 1),
        child: BottomNavigationBar(
          backgroundColor: Colors.transparent,
          items: <BottomNavigationBarItem>[
            const BottomNavigationBarItem(
              icon: Icon(Icons.home_outlined),
              label: 'Home'
            ),
            const BottomNavigationBarItem(
              icon: Icon(Icons.search_outlined),
              label: 'Discover'
            ),
            BottomNavigationBarItem(
              icon: Image.asset("assets/images/add_button.png"),
              label: ''
            ),
            const BottomNavigationBarItem(
              icon: Icon(Icons.inbox_outlined),
              label: 'Inbox'
            ),
            const BottomNavigationBarItem(
              icon: Icon(Icons.person_outline),
              label: 'Profile'
            ),
          ],
          type: BottomNavigationBarType.fixed,
          showUnselectedLabels: true,
          currentIndex: _selectedIndex,
          unselectedItemColor: Colors.white38,
          selectedItemColor: Colors.white,
          onTap: _onItemTapped,
        ),
      ),
    );
  }
}
