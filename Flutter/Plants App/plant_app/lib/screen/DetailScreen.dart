import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:plant_app/model/PlantItem.dart';
import 'package:readmore/readmore.dart';
import 'package:shared_preferences/shared_preferences.dart';

class DetailScreen extends StatefulWidget {
  static const routeName= "/detail_screen";
  static const MYFAVORITE = "MYFAVORITE";
  final PlantItem plantData;

  const DetailScreen({Key? key, required this.plantData}) : super(key: key);

  @override
  State<DetailScreen> createState() => _DetailScreenState();
}

class _DetailScreenState extends State<DetailScreen> {

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
    final mediaQuery = MediaQuery.of(context);

    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);

    return Scaffold(
        body: Container(
          padding: const EdgeInsets.only(top: 60),
          width: double.infinity,
          height: double.infinity,
          child: Stack(
            children: [
              Container(
                width: double.infinity,
                height: double.infinity,
                decoration: const BoxDecoration(
                image: DecorationImage(
                    image: AssetImage("assets/images/background.jpg"),
                fit: BoxFit.fill),
                ),
              ),
              Column(
                children: [
                  SizedBox(
                    width: double.infinity,
                    height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.8,
                    child: SingleChildScrollView(
                      child: Container(
                        padding: const EdgeInsets.only(left: 20, right: 20, bottom: 20, top: 10),
                        width: double.infinity,
                        child: Column(
                          children: [
                            Row(
                              children: [
                                ElevatedButton(
                                  onPressed: () {
                                    Navigator.pop(context);
                                  },
                                  child: const Icon(Icons.arrow_back, color: Colors.black),
                                  style: ElevatedButton.styleFrom(
                                    elevation: 0.0,
                                    shadowColor: Colors.transparent,
                                    shape: const CircleBorder(
                                      side: BorderSide(color: Color.fromRGBO(238, 240, 243, 1.0), width: 3),
                                    ),
                                    padding: const EdgeInsets.all(10),
                                    primary: Colors.transparent,
                                    onPrimary: Colors.white60,
                                  ),
                                ),
                                const Expanded(child: Text(
                                  'Detail',
                                  style: TextStyle(fontSize: 16),
                                  textAlign: TextAlign.center,
                                )),
                                FutureBuilder<bool>(
                                    future: isFavorite(widget.plantData.id.toString()),
                                    builder: (context, snapshot) {
                                      if (snapshot.hasData &&
                                          snapshot.data == true) {
                                        return ElevatedButton(
                                          onPressed: () {
                                            addUpdateFavorite(widget.plantData.id.toString());
                                          },
                                          child: const Icon(
                                              Icons.favorite,
                                              color: Colors.red
                                          ),
                                          style: ElevatedButton.styleFrom(
                                            elevation: 0.0,
                                            shadowColor: Colors.transparent,
                                            shape: const CircleBorder(
                                                side: BorderSide(color: Color.fromRGBO(238, 240, 243, 1.0), width: 3)
                                            ),
                                            padding: const EdgeInsets.all(10),
                                            primary: Colors.transparent,
                                            onPrimary: Colors.white60,
                                          ),
                                        );
                                      } else {
                                        return ElevatedButton(
                                          onPressed: () {
                                            addUpdateFavorite(widget.plantData.id.toString());
                                          },
                                          child: const Icon(
                                              Icons.favorite_outline,
                                              color: Colors.black
                                          ),
                                          style: ElevatedButton.styleFrom(
                                            elevation: 0.0,
                                            shadowColor: Colors.transparent,
                                            shape: const CircleBorder(
                                                side: BorderSide(color: Color.fromRGBO(238, 240, 243, 1.0), width: 3)
                                            ),
                                            padding: const EdgeInsets.all(10),
                                            primary: Colors.transparent,
                                            onPrimary: Colors.white60,
                                          ),
                                        );
                                      }
                                    }
                                ),
                              ],
                            ),
                            SizedBox(
                              width: double.infinity,
                              child: Image.asset(widget.plantData.imagePath),
                            ),
                            Row(
                              children: [
                                Expanded(
                                    child: Text(
                                      widget.plantData.name,
                                      style: const TextStyle(
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold
                                      ),
                                    )
                                ),
                                const Icon(
                                  Icons.star,
                                  size: 14,
                                  color: Color.fromRGBO(105, 171, 154, 1.0),
                                ),
                                Text(
                                  widget.plantData.rating.toString(),
                                  style: const TextStyle(
                                      color: Colors.black,
                                      fontWeight: FontWeight.bold
                                  ),
                                )
                              ],
                            ),
                            const SizedBox(height: 10,),
                            ReadMoreText(
                              widget.plantData.description,
                              trimLines: 4,
                              colorClickableText: Colors.blue,
                              trimMode: TrimMode.Line,
                              trimCollapsedText: 'Read More',
                              trimExpandedText: 'Show less',
                              moreStyle: const TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
                            ),
                            const SizedBox(height: 20,),
                            Table(
                              columnWidths: const <int, TableColumnWidth>{
                                0: FlexColumnWidth(),
                                1: FlexColumnWidth(),
                                2: FlexColumnWidth(),
                              },
                              defaultVerticalAlignment: TableCellVerticalAlignment.middle,
                              children: [
                                const TableRow(
                                  children: [
                                    TableCell(
                                        child: Text(
                                          "Size",
                                          style: TextStyle(
                                              color: Colors.grey,
                                              fontSize: 12
                                          ),
                                        )
                                    ),
                                    TableCell(
                                        child: Text(
                                          "Height",
                                          style: TextStyle(
                                              color: Colors.grey,
                                              fontSize: 12
                                          ),
                                        )
                                    ),
                                    TableCell(
                                        child: Text(
                                          "Humidity",
                                          style: TextStyle(
                                              color: Colors.grey,
                                              fontSize: 12
                                          ),
                                        )
                                    )
                                  ],
                                ),
                                TableRow(
                                  children: [
                                    TableCell(
                                        child: Text(
                                          widget.plantData.size.name.toString(),
                                          style: const TextStyle(
                                              color: Colors.black,
                                              fontSize: 14,
                                              fontWeight: FontWeight.bold
                                          ),
                                        )
                                    ),
                                    TableCell(
                                        child: Text(
                                          "${widget.plantData.height.toString()} cm",
                                          style: const TextStyle(
                                              color: Colors.black,
                                              fontSize: 14,
                                              fontWeight: FontWeight.bold
                                          ),
                                        )
                                    ),
                                    TableCell(
                                        child: Text(
                                          "${widget.plantData.humidity}%",
                                          style: const TextStyle(
                                              color: Colors.black,
                                              fontSize: 14,
                                              fontWeight: FontWeight.bold
                                          ),
                                        )
                                    )
                                  ],
                                )
                              ],
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                  Container(
                    padding: const EdgeInsets.all(20),
                    width: double.infinity,
                    height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.14,
                    child: Row(
                      children: [
                        SizedBox(
                          width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.35,
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              const Text(
                                "Price",
                                style: TextStyle(
                                    color: Colors.grey,
                                    fontSize: 14,
                                  fontWeight: FontWeight.bold
                                ),
                                textAlign: TextAlign.start,
                              ),
                              Text(
                                "\$${widget.plantData.price.toString()}",
                                style: const TextStyle(
                                  color: Colors.black,
                                  fontSize: 24,
                                  fontWeight: FontWeight.bold,
                                  fontFamily: "Montserrat"
                                ),
                                textAlign: TextAlign.start,
                              ),
                            ],
                          ),
                        ),
                        Expanded(
                          child: ElevatedButton(
                            onPressed: () {},
                            child: const Text(
                              "Add to cart",
                              style: TextStyle(
                                  color: Colors.white
                              ),
                            ),
                            style: ElevatedButton.styleFrom(
                              shape: const StadiumBorder(),
                              padding: const EdgeInsets.all(15),
                              primary: const Color.fromRGBO(105, 171, 154, 1.0), // <-- Button color
                              onPrimary: const Color.fromRGBO(105, 171, 154, 0.7), // <-- Splash color
                            ),
                          ),
                        )
                      ],
                    ),
                  ),
                ],
              )
             ],
          ),
        ),
    );
  }
}
