export class DadoUsuario {

  constructor(
    public id: number,
    public nome: string,
    private _token: string,
    public funcao: string,
    private _dataHoraExpiracao: Date
  ) { }

  get getToken() {
    if (!this._dataHoraExpiracao || new Date() > this._dataHoraExpiracao) {
      return null;
    }
    return this._token;
  }

  get getDataHora(){
    return this._dataHoraExpiracao;
  }
}