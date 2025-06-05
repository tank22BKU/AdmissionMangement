package DashBoard;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Object.*;
import Management.*;
import static java.lang.Thread.sleep;

public class options {

    static private ArrayList<Candidate> listCandidate = new ArrayList<Candidate>();
    static private ArrayList<Proctor> listProctor;
    static private ArrayList<Aspiration> listAspiration = new ArrayList<Aspiration>();

    static void printOptions() {
        System.out.println("\n----------Welcome to AdmissionMangement System----------");
        System.out.println("--                                                    --");
        System.out.println("--                                                    --");
        System.out.println("--                       @vta                         --");
        System.out.println("--                                                    --");
        System.out.println("--                                                    --");
        System.out.println("----------    Please Choose Your Option      -----------");
        System.out.println(
                "1.Thêm, cập nhật và xóa Nguyện vọng\n" +
                        "2.Tìm kiếm (Mã ngành, Tên ngành, Mã trường, Khối xét tuyển, Điểm đạt điều kiện) Nguyện vọng\n" +
                        "3.Hiển thị danh sách Nguyện vọng\n" +
                        "4.Thêm, cập nhật và xóa Thí sinh\n" +
                        "5.Thêm nguyện vọng cho Thí sinh\n" +
                        "6.Xóa nguyện vọng cho Thí sinh\n" +
                        "7.Tìm kiếm (Số báo danh, Họ tên, Giới tính, Điểm thi) Thí sinh\n" +
                        "8.Hiển thị danh sách Thí sinh (Bao gồm cả nguyện vọng của Thí sinh)\n" +
                        "9.Thêm, cập nhật và xóa Giám thị\n" +
                        "10.Tìm kiếm (Mã giám thị, Đơn vị công tác, Họ tên, Giới tính) Giám thị\n" +
                        "11.Hiển thị danh sách Giám thị\n" +
                        "12.Hiển thị danh sách Thí sinh trúng tuyển và điểm trúng tuyển theo Mã nguyện vọng\n" +
                        "13.Hiển thị danh sách Thí sinh trúng tuyển nguyện vọng theo Mã trường và Mã ngành của trường đó.\n" +
                        "14.Sắp xếp danh sách trúng tuyển theo Nguyện vọng (Sắp xếp giảm dần theo điểm thi)\n" +
                        "15.Ghi file danh sách Thí sinh trúng tuyển theo từng Mã nguyện vọng\n" +
                        "16.Đọc file danh sách Giám thị và lưu vào danh sách Giám thị\n" +
                        "17.Đọc file danh sách Thí sinh và lưu vào danh sách Thí sinh\n"
        );

        System.out.println("----------------------------------------------------------");
        System.out.println("----------------------------------------------------------");
        System.out.println("----------------------------------------------------------");
    }

    public static void main(String[] args) throws InterruptedException {
        printOptions();

        try{
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextInt()) {
                int value = sc.nextInt();
                if(value < 1 || value > 17 ){
                    System.out.println("\nPlease enter a number between 1 and 17");
                    continue;
                }

                System.out.println("\nYour choice is : " + value);

                switch (value) {
                    case 1: listAspiration = AspirationProcessing.gainCRUDRequest(listAspiration,listCandidate); break;
                    case 2: AspirationProcessing.searchForAspiration(listAspiration); break;
                    case 3: AspirationProcessing.printListAspiration(listAspiration); break;
                    case 4: listCandidate = CandidateCRUDProcessing.gainCRUDRequest(listCandidate); break;
                    case 5: listCandidate = CandidateCRUDProcessing.addNewAspirationForCandidate(listCandidate); break;
                    case 6: listCandidate = CandidateCRUDProcessing.deleteAspirationForCandidate(listCandidate); break;
                    case 7: break;
                    case 8: CandidateCRUDProcessing.printListCandiate(listCandidate); break;
                    case 9: break;
                    case 10: break;
                    case 11: break;
                    case 12: break;
                    case 13: break;
                    case 14: break;
                    case 15: break;
                    case 16: break;
                    case 17: break;
                }
                sleep(1000);
                printOptions();
            }
        } catch (InputMismatchException e) {
            System.out.println("\nQuang game !!! " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("Runtime Exception: " + e.getMessage());
        }

        System.out.println("Thank you for using Admission Mangement System");
    }
}
