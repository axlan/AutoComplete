#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QClipboard>
#include <iostream>




void MainWindow::HandleShortCut()
{
    QClipboard *clipboard = QApplication::clipboard();
    clipboard->setText(ui->lineEdit->text());
    std::cout<<"test2"<<std::endl;
}





MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);



    shortcut = new QxtGlobalShortcut(parent);
    connect(shortcut, SIGNAL(activated()), SLOT(HandleShortCut()));


}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_keySequenceEdit_editingFinished()
{
    shortcut->setShortcut(ui->keySequenceEdit->keySequence());
    std::cout<<"test1"<<std::endl;
}
