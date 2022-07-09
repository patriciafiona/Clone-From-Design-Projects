import 'package:flutter/material.dart';
import 'package:plant_app/model/PlantItem.dart';
import 'package:plant_app/screen/DetailScreen.dart';
import 'package:shared_preferences/shared_preferences.dart';

class CardPlantWidget extends StatefulWidget {
  final dynamic listData;

  const CardPlantWidget({Key? key, required this.listData}) : super(key: key);

  @override
  State<CardPlantWidget> createState() => _CardPlantWidget();
}

class _CardPlantWidget extends State<CardPlantWidget> {
  Future<void> addUpdateFavorite(String selectedId) async {
    final prefs = await SharedPreferences.getInstance();

    //try to check if fav list already exist or not
    var prefKeys =  prefs.getKeys();
    if(prefKeys.contains(DetailScreen.MYFAVORITE)){
      //already exist
      var myFav = prefs.getStringList(DetailScreen.MYFAVORITE);
      if(myFav != null && myFav.contains(selectedId) ){
        //remove from favorite
        myFav.remove(selectedId);
      }else{
        //add new
        myFav?.add(selectedId);
      }
      prefs.setStringList(DetailScreen.MYFAVORITE, myFav!);
    }else{
      //new
      List<String> myFav = [selectedId];
      prefs.setStringList(DetailScreen.MYFAVORITE, myFav);
    }

    //Update the fav btn
    setState(() {});
  }

  Future<bool> isFavorite(String id) async {
    final prefs = await SharedPreferences.getInstance();
    var prefKeys =  prefs.getKeys();
    if(prefKeys.contains(DetailScreen.MYFAVORITE)){
      var myFav = prefs.getStringList(DetailScreen.MYFAVORITE);
      if(myFav != null && myFav.contains(id) ){
        return true;
      }else{
        return false;
      }
    }else{
      return false;
    }
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        shrinkWrap: true,
        scrollDirection: Axis.horizontal,
        itemCount: widget.listData?.length,
        itemBuilder: (BuildContext context, int index) {
          var data = widget.listData?[index] as PlantItem;
          return GestureDetector(
            onTap: (){
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => DetailScreen(plantData: data) )
              );
            },
            child: Container(
              width: 220,
              padding: const EdgeInsets.all(10),
              child: Card(
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(15.0),
                ),
                color: const Color.fromRGBO(234, 233, 233, 1.0),
                child: Column(
                  children: [
                    Stack(
                      children: [
                        Container(
                          padding: const EdgeInsets.only(top: 10),
                          width: double.infinity,
                          height: 260,
                          child: Image.asset(data.imagePath)
                        ),
                        Container(
                          padding: const EdgeInsets.symmetric(vertical: 15, horizontal: 17),
                          width: double.infinity,
                          child: Text(
                            "\$${data.price.toString()}",
                            textAlign: TextAlign.right,
                            style: const TextStyle(
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        Positioned(
                          width: 160,
                          height: 50,
                          top: 60,
                          left: -47,
                          child: RotationTransition(
                            turns: const AlwaysStoppedAnimation(-90 / 360),
                            child: Text(
                              data.name,
                              style: const TextStyle(
                                fontWeight: FontWeight.w500,
                                fontSize: 14,
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                    Container(
                      padding: const EdgeInsets.only(left: 10, right: 10, bottom: 10),
                      child: Row(
                        children: [
                          Expanded(child:
                            ElevatedButton(
                              onPressed: () {},
                              child: const Text(
                                'Add to Cart',
                                style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 12
                                ),
                              ),
                              style: ElevatedButton.styleFrom(
                                shape: const StadiumBorder(),
                                primary: Colors.white,
                                onPrimary: Colors.grey,
                              ),
                            )
                          ),
                          FutureBuilder<bool>(
                              future: isFavorite(data.id.toString()),
                              builder: (context, snapshot) {
                                if (snapshot.hasData &&
                                    snapshot.data == true) {
                                  return ElevatedButton(
                                    onPressed: () {
                                      addUpdateFavorite(data.id.toString());
                                    },
                                    child: const Icon(
                                        Icons.favorite,
                                        color: Colors.red
                                    ),
                                    style: ElevatedButton.styleFrom(
                                      shape: const CircleBorder(),
                                      padding: const EdgeInsets.all(10),
                                      primary: Colors.black,
                                      onPrimary: Colors.grey,
                                    ),
                                  );
                                } else {
                                  return ElevatedButton(
                                    onPressed: () {
                                      addUpdateFavorite(data.id.toString());
                                    },
                                    child: const Icon(
                                        Icons.favorite_outline,
                                        color: Colors.white
                                    ),
                                    style: ElevatedButton.styleFrom(
                                      shape: const CircleBorder(),
                                      padding: const EdgeInsets.all(10),
                                      primary: Colors.black,
                                      onPrimary: Colors.grey,
                                    ),
                                  );
                                }
                              }
                          ),
                        ],
                      ),
                    )
                  ],
                ),
              ),
            ),
          );
        }
    );
  }
}
