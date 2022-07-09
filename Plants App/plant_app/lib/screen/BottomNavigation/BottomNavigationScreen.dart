import 'package:flutter/material.dart';
import 'package:plant_app/screen/BottomNavigation/FavoriteScreen.dart';
import 'package:plant_app/screen/BottomNavigation/HomeScreen.dart';
import 'package:plant_app/screen/BottomNavigation/ScanScreen.dart';
import 'package:plant_app/screen/BottomNavigation/ShoppingBagScreen.dart';
import 'package:plant_app/screen/BottomNavigation/UserScreen.dart';

class BottomNavigationScreen extends StatefulWidget {
  static const routeName = "/bottom_nav_screen";
  const BottomNavigationScreen({Key? key}) : super(key: key);

  @override
  State<BottomNavigationScreen> createState() => _BottomNavigationScreenState();
}

class _BottomNavigationScreenState extends State<BottomNavigationScreen> {
  int _selectedIndex = 0;
  static const List<Widget> _widgetOptions = <Widget>[
    HomeScreen(),
    FavoriteScreen(),
    ScanScreen(),
    ShoppingBagScreen(),
    UserScreen(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: Container(
        decoration: const BoxDecoration(
          borderRadius: BorderRadius.all(Radius.circular(30)),
          boxShadow: [
            BoxShadow(color: Colors.black38, spreadRadius: 0, blurRadius: 10),
          ],
        ),
        child: ClipRRect(
          borderRadius: const BorderRadius.all(
            Radius.circular(30.0)
          ),
          child: BottomNavigationBar(
            items: <BottomNavigationBarItem>[
              const BottomNavigationBarItem(
                icon: Icon(Icons.home),
                label: 'Home',
              ),
              const BottomNavigationBarItem(
                icon: Icon(Icons.favorite),
                label: 'Favorite',
              ),
              BottomNavigationBarItem(
                icon: Container(
                  decoration: BoxDecoration(
                      color: _selectedIndex != 2 ? const Color.fromRGBO(105, 171, 154, 1.0) :
                        const Color.fromRGBO(70, 131, 104, 1.0),
                      shape: BoxShape.circle),
                  child: const Padding(
                    padding: EdgeInsets.all(10.0),
                    child: Icon(
                      Icons.qr_code_scanner,
                      color: Colors.white,
                    ),
                  ),
                ),
                label: 'QR Scan',
              ),
              const BottomNavigationBarItem(
                icon: Icon(Icons.shopping_bag),
                label: 'Shopping Bag',
              ),
              const BottomNavigationBarItem(
                icon: Icon(Icons.person),
                label: 'User',
              ),
            ],
            currentIndex: _selectedIndex,
            selectedItemColor: const Color.fromRGBO(105, 171, 154, 1),
            unselectedItemColor: Colors.grey,
            onTap: _onItemTapped,
            type: BottomNavigationBarType.fixed,
            showSelectedLabels: false,
            showUnselectedLabels: false,
            backgroundColor: const Color.fromRGBO(245, 246, 246, 1),
          ),
        ),
      ),
    );
  }
}
