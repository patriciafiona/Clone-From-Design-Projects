import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

import '../../authentication/auth.dart';

class SettingsScreen extends StatefulWidget {
  static const routeName = '/settings';

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    final FirebaseAuth auth = FirebaseAuth.instance;
    final User? user = auth.currentUser;

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        elevation: 0,
        title: Text('Settings', style: Theme.of(context).textTheme.headline6, textAlign: TextAlign.center,),
      ),
      body: Container(
        decoration: const BoxDecoration(
          color: Colors.black87
        ),
        child: Container(
          width: double.infinity,
          child: SingleChildScrollView(
            padding: EdgeInsets.all(20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(
                  width: double.infinity,
                  child: Text(
                    'Free Account',
                    textAlign: TextAlign.center,
                    style: Theme.of(context).textTheme.headline6,
                  ),
                ),
                SizedBox(height: 15,),
                Container(
                  alignment: Alignment.center,
                  child: ElevatedButton(
                    onPressed: (){},
                    child: const Text('GO PREMIUM'),
                    style: ElevatedButton.styleFrom(
                      primary: Colors.white,
                      onPrimary: Colors.black,
                      elevation : 0,
                      textStyle: const TextStyle(
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.bold,
                          fontSize: 18,
                      ),
                      minimumSize: Size(
                          (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.55, 50
                      ),
                      shape: const StadiumBorder(),
                    ),
                  ),
                ),
                SizedBox(height: 30,),
                Row(
                  children: [
                    user?.photoURL != null ?
                    CircleAvatar(
                      radius: 30,
                      backgroundImage: NetworkImage(
                        user?.photoURL as String
                      )
                    ):
                    CircleAvatar(
                      radius: 15,
                      child: Image.asset('assets/images/placeholder_avatar.jpeg'),
                    ),
                    SizedBox(
                      width: 10,
                    ),
                    Expanded(child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          user?.displayName != null ? user?.displayName as String : '???',
                          style: TextStyle(
                              fontFamily: 'Gotham',
                              fontWeight: FontWeight.bold,
                              fontSize: 18,
                              color: Colors.white
                          ),
                        ),
                        SizedBox(height: 5,),
                        Text(
                          'View Profile',
                          style: TextStyle(
                              fontFamily: 'Gotham',
                              fontWeight: FontWeight.bold,
                              fontSize: 14,
                              color: Colors.grey
                          ),
                        ),
                      ],
                    )),
                    Container(
                      child: TextButton(
                        child: Icon(Icons.arrow_forward_ios),
                        onPressed: () {},
                        style: TextButton.styleFrom(
                          primary: Colors.white,
                        ),
                      ),
                    ),
                  ],
                ),
                SizedBox(height: 20,),
                Text(
                  'Data Saver',
                  style: Theme.of(context).textTheme.headline6,
                ),
              ],
            )
          )
        )
      )
    );
  }
}
