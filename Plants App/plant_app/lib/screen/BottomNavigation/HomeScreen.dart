import 'package:flutter/material.dart';
import 'package:intro_slider/intro_slider.dart';
import 'package:plant_app/model/DataDummy.dart';
import 'package:plant_app/model/PlantLocation.dart';
import 'package:plant_app/widget/CardPlantWidget.dart';

class HomeScreen extends StatefulWidget {
  static const routeName = "/home_screen";
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> with SingleTickerProviderStateMixin {
  late TabController _tabController;
  List<Slide> slides = [];
  late Function goToTab;

  var sortedByRating = DataDummy().plantsList;

  List<Widget> renderListCustomTabs() {
    return List.generate(
      slides.length,
          (index) => SizedBox(
        width: double.infinity,
        child: Container(
          margin: const EdgeInsets.only(bottom: 20.0),
          child: ListView(
            children: <Widget>[
              GestureDetector(
                child: Image.asset(
                  slides[index].pathImage!,
                  width: double.infinity,
                  height: 250,
                  fit: BoxFit.contain,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 4, vsync: this);
    _tabController.addListener(_handleTabSelection);

    slides.add(
      Slide(pathImage: "assets/images/banner_01.png"),
    );
    slides.add(
      Slide(pathImage: "assets/images/banner_02.png"),
    );

    sortedByRating.sort((a, b) => b.rating.compareTo(a.rating));
  }

  void _handleTabSelection() {
    setState(() {
    });
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      backgroundColor: Colors.white,
      body: SingleChildScrollView(
        child: Container(
          margin: const EdgeInsets.only(bottom: 120),
          padding: const EdgeInsets.only(top: 60, bottom: 15, left: 20),
          child: Column(
            children: [
              Row(children: [
                const Expanded(
                  child: Text(
                      "Find your \nfavorite plants",
                    style: TextStyle(
                      fontSize: 28,
                    ),
                  ),
                ),
                Container(
                  margin: const EdgeInsets.only(right: 20),
                  child: ButtonTheme(
                    minWidth: 50.0,
                    height: 100.0,
                    child: ElevatedButton(
                      onPressed: () {},
                      child: const Icon(Icons.search_outlined, color: Colors.black),
                      style: ElevatedButton.styleFrom(
                        elevation: 0.0,
                        shadowColor: Colors.transparent,
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(18.0),
                            side: const BorderSide(color: Color.fromRGBO(238, 240, 243, 1.0), width: 3)
                        ),
                        primary: Colors.transparent,
                        onPrimary: Colors.white60,
                      ),
                    ),
                  ),
                )
              ],
              ),
              Stack(
                children: [
                  Container(
                    height: 110,
                    margin: const EdgeInsets.only(top: 50, bottom: 15, left: 15, right: 35),
                    padding: const EdgeInsets.all(20),
                    width: double.infinity,
                    decoration: BoxDecoration(
                      color: const Color.fromRGBO(214, 234, 197, 1.0),
                      borderRadius: BorderRadius.circular(18.0),
                    ),
                  ),
                  Row(
                    children: [
                      Expanded(
                        child: Container(
                          padding: const EdgeInsets.only(left: 30, top: 50),
                          child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: const [
                            Text(
                              "30% OFF",
                              style: TextStyle(
                                  fontSize: 20,
                                  fontFamily: "Montserrat",
                                  fontWeight: FontWeight.bold,
                                  color: Colors.black
                              ),
                            ),
                            SizedBox(
                              height: 10,
                            ),
                            Text(
                              "02-23 July",
                              style: TextStyle(
                                  color: Color.fromRGBO(71, 80, 61, 1.0),
                                  fontFamily: "Montserrat",
                                  fontWeight: FontWeight.w500
                              ),
                            )
                          ],
                      ),
                        ),
                      ),
                      Container(
                        margin: const EdgeInsets.only(right: 10),
                        width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.4,
                        child: Image.asset("assets/images/plant_05.png"),
                      )
                    ],
                  ),
                ],
              ),
              const SizedBox(height: 10,),
              TabBar(
                isScrollable: true,
                labelPadding: const EdgeInsets.symmetric(horizontal: 5),
                controller: _tabController,
                unselectedLabelColor: Colors.grey,
                indicatorSize: TabBarIndicatorSize.label,
                indicator: BoxDecoration(
                  borderRadius: BorderRadius.circular(50),
                  border: Border.all(color: Colors.black, width: 2.0),
                ),
                tabs: [
                  Tab(
                    child: Container(
                      width: 65,
                      height: 38,
                      decoration: _tabController.index != 0 ? BoxDecoration(
                          borderRadius: BorderRadius.circular(50),
                          border: Border.all(color: const Color.fromRGBO(239, 240, 244, 1.0), width: 1)
                      ): null,
                      child: Align(
                        alignment: Alignment.center,
                        child: Text(
                          "All",
                          style: _tabController.index == 0 ?
                            const TextStyle(fontWeight: FontWeight.bold):
                            const TextStyle(fontWeight: FontWeight.normal),
                        ),
                      ),
                    ),
                  ),
                  Tab(
                    child: Container(
                      width: 85,
                      height: 38,
                      decoration: _tabController.index != 1 ? BoxDecoration(
                          borderRadius: BorderRadius.circular(50),
                          border: Border.all(color: const Color.fromRGBO(239, 240, 244, 1.0), width: 1)
                      ): null,
                      child: Align(
                        alignment: Alignment.center,
                        child: Text(
                          "Indoor",
                          style: _tabController.index == 1 ?
                            const TextStyle(fontWeight: FontWeight.bold):
                            const TextStyle(fontWeight: FontWeight.normal),
                        ),
                      ),
                    ),
                  ),
                  Tab(
                    child: Container(
                      width: 95,
                      height: 38,
                      decoration: _tabController.index != 2 ? BoxDecoration(
                          borderRadius: BorderRadius.circular(50),
                          border: Border.all(color: const Color.fromRGBO(239, 240, 244, 1.0), width: 1)
                      ): null,
                      child: Align(
                        alignment: Alignment.center,
                        child: Text(
                          "Outdoor",
                          style: _tabController.index == 2 ?
                            const TextStyle(fontWeight: FontWeight.bold):
                            const TextStyle(fontWeight: FontWeight.normal),
                        ),
                      ),
                    ),
                  ),
                  Tab(
                    child: Container(
                      width: 100,
                      height: 38,
                      decoration: _tabController.index != 3 ? BoxDecoration(
                          borderRadius: BorderRadius.circular(50),
                          border: Border.all(color: const Color.fromRGBO(239, 240, 244, 1.0), width: 1)
                      ): null,
                      child: Align(
                        alignment: Alignment.center,
                        child: Text(
                          "Popular",
                          style: _tabController.index == 3 ?
                            const TextStyle(fontWeight: FontWeight.bold):
                            const TextStyle(fontWeight: FontWeight.normal),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
              SizedBox(
                height: 350.0,
                child: TabBarView(
                  controller: _tabController,
                  children: <Widget>[
                    CardPlantWidget(listData: DataDummy().plantsList),
                    CardPlantWidget(listData: DataDummy().plantsList.where((i) => i.location == PlantLocation.Indoor).toList() ),
                    CardPlantWidget(listData: DataDummy().plantsList.where((i) => i.location == PlantLocation.Outdoor).toList() ),
                    CardPlantWidget(listData: sortedByRating),
                  ],
                ),
              ),
              Container(
                margin: const EdgeInsets.only(top: 15, bottom: 10),
                width: double.infinity,
                child: const Text(
                  "What's New?",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 18
                  ),
                  textAlign: TextAlign.start,
                ),
              ),
              Container(
                width: double.infinity,
                height: 300,
                margin: const EdgeInsets.only(right: 20),
                child: MediaQuery.removePadding(
                  context: context,
                  removeTop: true,
                  child: IntroSlider(
                    // Dot indicator
                    colorDot: const Color.fromRGBO(214, 234, 196, 1.0),
                    sizeDot: 10.0,
                    typeDotAnimation: DotSliderAnimation.SIZE_TRANSITION,

                    // Tabs
                    listCustomTabs: renderListCustomTabs(),
                    refFuncGoToTab: (refFunc) {
                      goToTab = refFunc;
                    },

                    // Behavior
                    scrollPhysics: const BouncingScrollPhysics(),

                    // Show or hide status bar
                    hideStatusBar: true,
                  ),
                ),
              ),
            ],
          ),
        ),
      )
    );
  }
}
