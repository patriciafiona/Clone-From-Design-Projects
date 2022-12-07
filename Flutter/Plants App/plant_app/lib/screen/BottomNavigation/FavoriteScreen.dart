import 'package:flutter/material.dart';
import 'package:plant_app/widget/CardFavoritePlantWidget.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../model/DataDummy.dart';
import '../DetailScreen.dart';

class FavoriteScreen extends StatefulWidget {
  const FavoriteScreen({Key? key}) : super(key: key);

  @override
  State<FavoriteScreen> createState() => _FavoriteScreenState();
}

class _FavoriteScreenState extends State<FavoriteScreen> {

  refresh() {
    setState(() {});
  }

  Future<List<String>?> getFavorites() async{
    final prefs = await SharedPreferences.getInstance();
    var prefKeys =  prefs.getKeys();
    if(prefKeys.contains(DetailScreen.MYFAVORITE)) {
      //already exist
      return prefs.getStringList(DetailScreen.MYFAVORITE);
    }else{
      return null;
    }
  }

  Widget noFavoriteInformation(double top, double width){
    return Column(
        children: [
          Container(
            margin: EdgeInsets.only(
                top: top,
                bottom: 30),
            width: width,
            child: Image.asset("assets/images/add_favorite.png"),
          ),
          const Text(
            "No Favorite Yet",
            style: TextStyle(
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: Colors.black
            ),
          ),
          const SizedBox(height: 10),
          const Text(
            "Save your favorite plant \nby click heart symbol button",
            style: TextStyle(
                fontSize: 14,
                color: Colors.grey
            ),
            textAlign: TextAlign.center,
          ),
        ]
    );
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Container(
          width: double.infinity,
          margin: const EdgeInsets.only(bottom: 120),
          padding: const EdgeInsets.only(top: 60, bottom: 15, left: 20, right: 20),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                SizedBox(
                  width: double.infinity,
                  height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.07,
                  child: Row(
                    children: const [
                      Icon(
                        Icons.favorite,
                        color: Colors.red,
                      ),
                      SizedBox(width: 5),
                      Text(
                        "Favorite",
                        style: TextStyle(
                          fontSize: 28,
                          fontWeight: FontWeight.bold,
                        ),
                        textAlign: TextAlign.start,
                      ),
                    ],
                  ),
                ),
                FutureBuilder<List<String>?>(
                  future: getFavorites(),
                  builder: (context, snapshot) {
                    if (snapshot.hasData && snapshot.data != null) {
                      var listFav = snapshot.data as List<String>;
                      if(listFav.isNotEmpty){
                        return SizedBox(
                          height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.93,
                          child: MediaQuery.removePadding(
                            context: context,
                            removeTop: true,
                            child: ListView.builder(
                              shrinkWrap: true,
                              itemCount: listFav.length,
                              scrollDirection: Axis.vertical,
                              itemBuilder: (BuildContext context, int index) {
                                var data = DataDummy().plantsList.where((i) => i.id.toString() == listFav[index]).toList().first;
                                return CardFavoritePlantWidget(notifyParent: refresh, favoritePlant: data);
                              }
                            ),
                          ),
                        );
                      }else{
                        return noFavoriteInformation(
                            (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.2,
                            (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.7
                        );
                      }
                    }else{
                      return noFavoriteInformation(
                            (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.2,
                            (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.7
                      );
                    }
                  }
                ),
              ],
            ),
          ),
        )
    );
  }
}
