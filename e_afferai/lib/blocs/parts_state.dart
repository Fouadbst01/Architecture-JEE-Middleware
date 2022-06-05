part of 'parts_bloc.dart';

@immutable
abstract class PartsState extends Equatable{
  const PartsState();

  @override
  List<Object?> get props => [];
}

class PartsInitial extends PartsState {}

class PartsLoading extends PartsState {}

class PartsLoaded extends PartsState {
  final List<Part> partsModel;
  const PartsLoaded(this.partsModel);
}

class PartsError extends PartsState {
  final String? message;
  const PartsError(this.message);
}