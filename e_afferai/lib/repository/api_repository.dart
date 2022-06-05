import '../models/part_model.dart';
import '../resources/api_provider.dart';

class ApiRepository {
  final _provider = ApiProvider();

  Future<List<Part>>fetchPartsList() {
    return _provider.fetchPartsList();
  }

}

class NetworkError extends Error {}