import { Component, OnInit } from '@angular/core';
import { FlatTreeControl } from '@angular/cdk/tree';
import { MatTreeFlatDataSource, MatTreeFlattener, MatTreeModule } from '@angular/material/tree';
import { DirectoryService } from '../../services/directory.service';
import { of as observableOf } from 'rxjs';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
/** Estrutura de dados para o nó da árvore */
interface FileNode {
  nome: string;
  children?: FileNode[];
}

/** Estrutura para o nó achatado, que a árvore do Angular Material espera */
interface FlatNode {
  nome: string;
  level: number;
  expandable: boolean;
}

@Component({
  selector: 'app-directory-list',
  standalone: true,
  imports: [
    MatTreeModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './directory-list.component.html',
  styleUrls: ['./directory-list.component.scss']
})
export class DirectoryListComponent implements OnInit {
  private _transformer = (node: FileNode, level: number) => ({
    nome: node.nome,
    level: level,
    expandable: !!node.children && node.children.length > 0,
  });

  treeControl = new FlatTreeControl<FlatNode>(
    node => node.level,
    node => node.expandable
  );

  treeFlattener = new MatTreeFlattener(
    this._transformer,
    node => node.level,
    node => node.expandable,
    node => node.children
  );

  dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

  constructor(private directoryService: DirectoryService, private router: Router) {}

  ngOnInit(): void {
    this.directoryService.getDirectories().subscribe((data) => {
      this.dataSource.data = this.buildFileTree(data);
    });
  }

  hasChild = (_: number, node: FlatNode) => node.expandable;

  /** Função para construir a árvore de diretórios e arquivos */
  buildFileTree(data: any[]): FileNode[] {
    return data.map((dir) => ({
      nome: dir.nome,
      children: [
        ...(dir.subdiretorios || []).map((subdir: any) => ({
          nome: subdir.nome,
          children: (subdir.arquivos || []).map((file: any) => ({
            nome: file.nome
          }))
        })),
        ...(dir.arquivos || []).map((file: any) => ({
          nome: file.nome
        }))
      ]
    }));
  }

  goToAbout() {
    this.router.navigate(['/sobre-mim']);
  }
}
