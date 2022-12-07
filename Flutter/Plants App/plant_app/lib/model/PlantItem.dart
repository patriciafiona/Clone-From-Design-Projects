import 'package:plant_app/model/PlantLocation.dart';
import 'package:plant_app/model/SizeType.dart';

class PlantItem{
  int id;
  String name;
  double price;
  String imagePath;
  double rating;
  String description;
  SizeType size;
  double height;
  PlantLocation location;
  int humidity;

  PlantItem({
    required this.id,
    required this.name,
    required this.price,
    required this.height,
    required this.description,
    required this.humidity,
    required this.rating,
    required this.size,
    required this.location,
    required this.imagePath
  });
}