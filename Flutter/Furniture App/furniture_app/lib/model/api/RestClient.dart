import 'package:http/http.dart' as http;
import 'dart:async';

abstract class RestClient {

  static Future getFurnitures() {
    return http.get(Uri.parse("https://raw.githubusercontent.com/patriciafiona/patriciafiona.github.io/main/hosting/JSON/furnitures.json"));
  }
}