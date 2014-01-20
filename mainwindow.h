#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QxtWidgets/qxtglobalshortcut.h>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void HandleShortCut();

    void on_keySequenceEdit_editingFinished();

private:

    QxtGlobalShortcut* shortcut;

    Ui::MainWindow *ui;
};

#endif // MAINWINDOW_H
