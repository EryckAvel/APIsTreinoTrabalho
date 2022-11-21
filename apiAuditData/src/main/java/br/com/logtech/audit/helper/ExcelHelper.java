package br.com.logtech.audit.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import br.com.logtech.audit.model.Arquivos;
import br.com.logtech.audit.model.Evolucao;
import br.com.logtech.audit.model.MetaAno;
import br.com.logtech.audit.model.MetaMes;
import br.com.logtech.audit.model.Movimentacao;
import br.com.logtech.audit.model.MovimentacaoPrevista;
import br.com.logtech.audit.model.PlanoConta;
import br.com.logtech.audit.model.PremissaFinanceira;
import br.com.logtech.audit.model.PremissaPercentual;
import br.com.logtech.audit.model.RealizadoMesAno;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<PlanoConta> excelToPlanoConta(InputStream is, Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<PlanoConta> planoContas = new ArrayList<PlanoConta>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				PlanoConta plano = new PlanoConta();

				int cellIdx = 0;
				int colcount = 2;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							plano.setCodigo(String.valueOf(value));
						} else {
							plano.setCodigo(currentCell.getStringCellValue());
						}
						break;
					case 1:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							plano.setCodigoPai(String.valueOf(value));
						} else {
							String value = currentCell.getStringCellValue();
							if (value != "-") {
								plano.setCodigoPai(value);
							}
						}
						break;
					case 2:
						plano.setDescricao(currentCell.getStringCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}
				plano.setArquivo(arquivo);
				planoContas.add(plano);
			}
			workbook.close();
			return planoContas;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	public static List<MetaMes> excelToMetaMes(InputStream is, Integer mes, Integer ano, Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<MetaMes> metas = new ArrayList<MetaMes>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				MetaMes metaMes = new MetaMes();

				int cellIdx = 0;
				int colcount = 2;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							metaMes.setCodigo(String.valueOf(value));
						} else {
							metaMes.setCodigo(currentCell.getStringCellValue());
						}
						break;
					case 1:
						metaMes.setDescricao(currentCell.getStringCellValue());
						break;
					case 2:
						metaMes.setValor(currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}
				metaMes.setAno(ano);
				metaMes.setMes(mes);
				metaMes.setArquivo(arquivo);
				metas.add(metaMes);
			}
			workbook.close();
			return metas;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	public static List<MetaAno> excelToMetaAno(InputStream is, Integer ano, Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<MetaAno> metas = new ArrayList<MetaAno>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				MetaAno metaAno = new MetaAno();

				int cellIdx = 0;
				int colcount = 2;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							metaAno.setCodigo(String.valueOf(value));
						} else {
							metaAno.setCodigo(currentCell.getStringCellValue());
						}
						break;
					case 1:
						metaAno.setDescricao(currentCell.getStringCellValue());
						break;
					case 2:
						metaAno.setValor(currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}
				metaAno.setAno(ano);
				metaAno.setArquivo(arquivo);
				metas.add(metaAno);
			}
			workbook.close();
			return metas;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	public static List<Evolucao> excelToEvolucao(InputStream is, Integer ano, Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<Evolucao> evolucoes = new ArrayList<Evolucao>();

			int rowNumber = 0;
			int anoBd = ano;
			String codigo = "";
			String desc = "";
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				int cellIdx = 0;
				int colcount = 11;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							codigo = String.valueOf(value);
						} else {
							codigo = currentCell.getStringCellValue();
						}
						break;
					case 1:
						desc = currentCell.getStringCellValue();
						break;
					case 2:
						;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 3:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 4:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 5:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 6:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 7:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 8:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 9:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 10:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 11:
						anoBd = anoBd + 1;
						evolucoes.add(
								newEvolucao(codigo, desc, (int) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					default:
						break;
					}
					cellIdx++;
				}
			}
			workbook.close();
			return evolucoes;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	public static List<PremissaFinanceira> excelToPremissaFinanceira(InputStream is, Integer ano, Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<PremissaFinanceira> premissas = new ArrayList<PremissaFinanceira>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				int anoBd = ano;
				String codigo = "";
				String desc = "";

				Iterator<Cell> cellsInRow = currentRow.iterator();

				int cellIdx = 0;
				int colcount = 11;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							codigo = String.valueOf(value);
						} else {
							codigo = currentCell.getStringCellValue();
						}
						break;
					case 1:
						desc = currentCell.getStringCellValue();
						break;
					case 2:
						;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));

						break;
					case 3:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 4:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 5:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 6:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 7:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 8:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 9:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 10:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 11:
						anoBd = anoBd + 1;
						premissas.add(
								newPremFin(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					default:
						break;
					}
					cellIdx++;
				}
			}
			workbook.close();
			return premissas;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	public static List<PremissaPercentual> excelToPremissaPercentual(InputStream is, Integer ano, Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<PremissaPercentual> premissas = new ArrayList<PremissaPercentual>();

			int rowNumber = 0;

			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				int anoBd = ano;
				String codigo = "";
				String desc = "";

				Iterator<Cell> cellsInRow = currentRow.iterator();

				int cellIdx = 0;
				int colcount = 11;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							codigo = String.valueOf(value);
						} else {
							codigo = currentCell.getStringCellValue();
						}
						break;
					case 1:
						desc = currentCell.getStringCellValue();
						break;
					case 2:
						;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));

						break;
					case 3:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 4:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 5:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 6:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 7:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 8:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 9:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 10:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					case 11:
						anoBd = anoBd + 1;
						premissas.add(
								newPremPerc(codigo, desc, (Double) currentCell.getNumericCellValue(), anoBd, arquivo));
						break;
					default:
						break;
					}
					cellIdx++;
				}
			}
			workbook.close();
			return premissas;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	public static List<Movimentacao> excelToMovimentacao(InputStream is, Integer ano, Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<Movimentacao> movimentacao = new ArrayList<Movimentacao>();

			int rowNumber = 0;

			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				String codigo = "";
				String desc = "";

				Iterator<Cell> cellsInRow = currentRow.iterator();

				int cellIdx = 0;
				int colcount = 13;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							codigo = String.valueOf(value);
						} else {
							codigo = currentCell.getStringCellValue();
						}
						break;
					case 1:
						desc = currentCell.getStringCellValue();
						break;
					case 2:
						;
						movimentacao.add(
								newMovimentacao(codigo, 1, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 3:
						movimentacao.add(
								newMovimentacao(codigo, 2, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 4:
						movimentacao.add(
								newMovimentacao(codigo, 3, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 5:
						movimentacao.add(
								newMovimentacao(codigo, 4, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 6:
						movimentacao.add(
								newMovimentacao(codigo, 5, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 7:
						movimentacao.add(
								newMovimentacao(codigo, 6, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 8:
						movimentacao.add(
								newMovimentacao(codigo, 7, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 9:
						movimentacao.add(
								newMovimentacao(codigo, 8, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 10:
						movimentacao.add(
								newMovimentacao(codigo, 9, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 11:
						movimentacao.add(
								newMovimentacao(codigo, 10, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 12:
						movimentacao.add(
								newMovimentacao(codigo, 11, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 13:
						movimentacao.add(
								newMovimentacao(codigo, 12, ano, (Double) currentCell.getNumericCellValue(), arquivo));
						break;
					default:
						break;
					}
					cellIdx++;
				}
			}
			workbook.close();
			return movimentacao;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	public static List<RealizadoMesAno> excelToRealizadoMesAno(InputStream is, Integer mes, Integer ano,
			Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<RealizadoMesAno> realizados = new ArrayList<RealizadoMesAno>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				RealizadoMesAno realizado = new RealizadoMesAno();

				int cellIdx = 0;
				int colcount = 2;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							realizado.setCodigo(String.valueOf(value));
						} else {
							realizado.setCodigo(currentCell.getStringCellValue());
						}
						break;
					case 1:
						realizado.setDescricao(currentCell.getStringCellValue());
						break;
					case 2:
						realizado.setValor(currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}
				realizado.setAno(ano);
				realizado.setMes(mes);
				realizado.setArquivo(arquivo);
				realizados.add(realizado);
			}
			workbook.close();
			return realizados;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	public static List<MovimentacaoPrevista> excelToMovimentacaoPrevista(InputStream is, Integer ano,
			Arquivos arquivo) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<MovimentacaoPrevista> movimentacao = new ArrayList<MovimentacaoPrevista>();

			int rowNumber = 0;

			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				String codigo = "";
				String desc = "";

				Iterator<Cell> cellsInRow = currentRow.iterator();

				int cellIdx = 0;
				int colcount = 13;
				while (cellIdx <= colcount) {
					Cell currentCell = cellsInRow.next();
					cellIdx = currentCell.getColumnIndex();

					switch (cellIdx) {
					case 0:
						if (currentCell.getCellType().toString() == "NUMERIC") {
							int value = (int) currentCell.getNumericCellValue();
							codigo = String.valueOf(value);
						} else {
							codigo = currentCell.getStringCellValue();
						}
						break;
					case 1:
						desc = currentCell.getStringCellValue();
						break;
					case 2:
						;
						movimentacao.add(newMovimentacaoPrevista(codigo, 1, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 3:
						movimentacao.add(newMovimentacaoPrevista(codigo, 2, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 4:
						movimentacao.add(newMovimentacaoPrevista(codigo, 3, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 5:
						movimentacao.add(newMovimentacaoPrevista(codigo, 4, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 6:
						movimentacao.add(newMovimentacaoPrevista(codigo, 5, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 7:
						movimentacao.add(newMovimentacaoPrevista(codigo, 6, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 8:
						movimentacao.add(newMovimentacaoPrevista(codigo, 7, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 9:
						movimentacao.add(newMovimentacaoPrevista(codigo, 8, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 10:
						movimentacao.add(newMovimentacaoPrevista(codigo, 9, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 11:
						movimentacao.add(newMovimentacaoPrevista(codigo, 10, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 12:
						movimentacao.add(newMovimentacaoPrevista(codigo, 11, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					case 13:
						movimentacao.add(newMovimentacaoPrevista(codigo, 12, ano,
								(Double) currentCell.getNumericCellValue(), arquivo));
						break;
					default:
						break;
					}
					cellIdx++;
				}
			}
			workbook.close();
			return movimentacao;

		} catch (IOException e) {
			throw new RuntimeException("falha ao converter arquivo Excel: " + e.getMessage());
		}
	}

	private static Evolucao newEvolucao(String codigo, String descricao, Integer meta, Integer ano, Arquivos arquivo) {
		Evolucao evolucao = new Evolucao(null, codigo, descricao, meta, ano, arquivo);
		return evolucao;
	}

	private static PremissaFinanceira newPremFin(String codigo, String descricao, Double valor, Integer ano,
			Arquivos arquivo) {
		PremissaFinanceira premissa = new PremissaFinanceira(null, codigo, descricao, valor, ano, arquivo);
		return premissa;
	}

	private static PremissaPercentual newPremPerc(String codigo, String descricao, Double valor, Integer ano,
			Arquivos arquivo) {
		PremissaPercentual premissa = new PremissaPercentual(null, codigo, descricao, valor, ano, arquivo);
		return premissa;
	}

	private static Movimentacao newMovimentacao(String codigo, Integer mes, Integer ano, Double valor,
			Arquivos arquivo) {

		PlanoConta plano = new PlanoConta();
		plano.setCodigo(codigo);
		Movimentacao mov = new Movimentacao(null, mes, ano, valor, plano, arquivo);
		return mov;
	}

	private static MovimentacaoPrevista newMovimentacaoPrevista(String codigo, Integer mes, Integer ano, Double valor,
			Arquivos arquivo) {

		PlanoConta plano = new PlanoConta();
		plano.setCodigo(codigo);
		MovimentacaoPrevista mov = new MovimentacaoPrevista(null, mes, ano, valor, plano, arquivo);
		return mov;
	}

}
