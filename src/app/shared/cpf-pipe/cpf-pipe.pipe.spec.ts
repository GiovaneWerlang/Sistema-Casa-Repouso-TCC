import { CpfPipe } from './cpf-pipe.pipe';

describe('CpfPipe', () => {
  it('create an instance', () => {
    const pipe = new CpfPipe();
    expect(pipe).toBeTruthy();
  });
  it('transform correctly', () => {
    const pipe = new CpfPipe();
    expect(pipe.transform('12345678912')).toEqual('123.456.789-12');
  });
  it('error when wrong size', () => {
    const pipe = new CpfPipe();
    expect(pipe.transform('123456789121')).toEqual('error');
  });
});
