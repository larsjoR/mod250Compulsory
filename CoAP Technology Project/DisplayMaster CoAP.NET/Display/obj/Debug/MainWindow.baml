   M S B A M L   `   `   `  ����B  >Display, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null!clr-namespace:DisplayDisplay  5   	   -clr-namespace:Display.ModelDisplay.Model  5   	       Display.MainWindow   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   'localclr-namespace:Display     5   	   *vmclr-namespace:Display.Model     5   	     � Title$  IoT Dashboard��5	   	   $��512.745��6   $��723.039��60   .��5
      � .+�5   
   �� -   5         ��Name$ 
boxOverlay��5      
���   �$��Stretch=�65   
���   �$��Stretch=�6c   $y�Visible=�6�   $��2��6�   � 5   
    � RowDefinitions 5      �� 5      $
��195* �6    �� 5      $
��287* �6    .+�5      �� -   5         ��Name$ Temperature��5      $
��Left=�6.   $��
65,156,0,0q�6I   $
��Wrap=�6]   $	��404��6�   $��77��6�   $	��Top=�6�   $��36(�6�   $��2��6�   ��6q   ��  �� Path$ Temperature� �� Mode$ OneWay=�6�   �� 5      -   5        $ InfoText��5      $
��Left=�6*   $��
65,101,0,0q�6E   $
��Wrap=�6Y   $��Temperature:��6m   $	��Top=�6�   $��50��6�   $	��252��6�   $��36(�6�   5      5      5      