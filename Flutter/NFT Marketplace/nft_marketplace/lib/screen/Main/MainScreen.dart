import 'package:flutter/material.dart';

import 'Find/FindTabScreen.dart';
import 'Profile/ProfileTabScreen.dart';
import 'Trade/TradeTabScreen.dart';
import 'Wallet/WalletTabScreen.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({Key? key}) : super(key: key);
  static const routeName = '/main_screen';

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int _selectedIndex = 0;

  static const List<Widget> _pageTabOption = <Widget>[
    FindTabScreen(),
    TradeTabScreen(),
    ProfileTabScreen(),
    WalletTabScreen()
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
          image: DecorationImage(
            image: AssetImage("assets/images/background.png"),
            fit: BoxFit.cover,
          ),
        ),
        width: double.infinity,
        child: SingleChildScrollView(
          child: _pageTabOption.elementAt(_selectedIndex),
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home_outlined),
            label: 'Find',
            backgroundColor: Colors.black,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.query_stats),
            label: 'Trade',
            backgroundColor: Colors.black,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person_outline),
            label: 'Profile',
            backgroundColor: Colors.black,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.wallet_outlined),
            label: 'Wallet',
            backgroundColor: Colors.black,
          ),
        ],
        currentIndex: _selectedIndex,
        unselectedItemColor: Colors.white38,
        selectedItemColor: Colors.white,
        onTap: _onItemTapped,
      ),
    );
  }
}
