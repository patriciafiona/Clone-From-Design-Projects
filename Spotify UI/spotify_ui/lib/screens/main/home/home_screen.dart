import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:spotify_ui/widgets/playlist_item.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _greetingText() {
    var hour = DateTime.now().hour;
    if (hour < 12) {
      return 'Good Morning';
    }
    if (hour < 17) {
      return 'Good Afternoon';
    }
    return 'Good Evening';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
        decoration: const BoxDecoration(
        gradient: RadialGradient(
          center: Alignment(-0.5, -1.5), // near the top right
          radius: 0.9,
          stops: <double>[0.2, 1.0],
          colors: [
            Color.fromRGBO(153, 87, 3, 1),
            Colors.black87,
          ],
        )
      ),
      child: Container(
        padding: EdgeInsets.all(20),
        width: double.infinity,
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                Spacer(),
                Container(
                  margin: EdgeInsets.only(top: 10),
                  child: TextButton(
                    child: Icon(Icons.settings),
                    onPressed: () {},
                    style: TextButton.styleFrom(
                      primary: Colors.white,
                    ),
                  ),
                ),
              ],
            ),
            Text(
              _greetingText(),
              style: TextStyle(
                fontFamily: 'Gotham',
                fontWeight: FontWeight.bold,
                fontSize: 30,
                color: Colors.white
              ),
            ),
            SizedBox(height: 10,),
            Expanded(
              child: StreamBuilder(
                stream: FirebaseFirestore.instance.collection('jump_back_in').snapshots(),
                builder: (ctx, AsyncSnapshot<QuerySnapshot> dataSnapshot) {
                  if (dataSnapshot.connectionState == ConnectionState.waiting) {
                    return Center(
                      child: CircularProgressIndicator(),
                    );
                  }
                  final jumpBackInDocs = dataSnapshot.data?.docs;
                  return ListView.builder(
                    shrinkWrap: true,
                    scrollDirection: Axis.horizontal,
                    itemCount: jumpBackInDocs?.length,
                    itemBuilder: (BuildContext context, int index) {
                      var jump_back_in_data = jumpBackInDocs?[index].data() as Map<String, dynamic>;
                      return PlaylistItem(playlist: jump_back_in_data);
                    }
                  );
                }
              )
            )
          ],)
        )
      ),
    );
  }
}
