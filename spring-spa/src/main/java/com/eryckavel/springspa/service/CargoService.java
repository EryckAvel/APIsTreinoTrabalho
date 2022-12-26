package com.eryckavel.springspa.service;

import com.eryckavel.springspa.model.Cargo;
import com.eryckavel.springspa.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CargoService {

    @Autowired
    CargoRepository repository;

    private Boolean system = true;

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual metodo de cargo deseja ultilizar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Vizualizar");
            System.out.println("4 - Deletar");
            int action = scanner.nextInt();
            switch (action){
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    vizualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    public void vizualizar(){
        Iterable<Cargo> cargos = repository.findAll();
        cargos.forEach(cargo -> {
            System.out.println(cargos);
        });
    }

    public void salvar(Scanner scanner){
        System.out.println("Descricao do cargo:");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        repository.save(cargo);
        System.out.println("Salvo!");
    }

    public void atualizar(Scanner scanner){
        System.out.println("id");
        int id = scanner.nextInt();
        System.out.println("Descricao do cargo:");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        repository.save(cargo);

    }

    public void deletar(Scanner scanner){
        System.out.println("Digite o ID:");
        int id = scanner.nextInt();
        repository.deleteById((id));
        System.out.println("Deletado");
    }

}
