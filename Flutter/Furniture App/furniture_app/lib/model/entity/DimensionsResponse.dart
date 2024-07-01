import 'package:json_annotation/json_annotation.dart';

@JsonSerializable()
class Dimensions {
  int? width;
  int? height;
  int? dimension;

  Dimensions({this.width, this.height, this.dimension});

  Dimensions.fromJson(Map<String, dynamic> json) {
    width = json['width'];
    height = json['height'];
    dimension = json['dimension'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['width'] = width;
    data['height'] = height;
    data['dimension'] = dimension;
    return data;
  }
}